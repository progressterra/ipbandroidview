package com.progressterra.ipbandroidview.pages.videopie

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import kotlinx.coroutines.delay

@OptIn(UnstableApi::class)
@Composable
fun VideoPieScreen(
    modifier: Modifier, state: VideoPieScreenState, useComponent: UseVideoPieScreen
) {
    ThemedLayout(
        modifier = modifier
    ) { _, _ ->
        StateColumn(state = state.screen, useComponent = useComponent) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                val context = LocalContext.current
                var firstTime by remember { mutableStateOf(true) }
                var animationEnabled by remember { mutableStateOf(false) }
                val exoPlayer = remember { ExoPlayer.Builder(context).build() }
                val animationAlpha by
                animateFloatAsState(targetValue = if (animationEnabled) 1F else 0F,
                    animationSpec = tween(durationMillis = 600, easing = LinearEasing),
                    label = "player visibility",
                    finishedListener = {
                        animationEnabled = false
                    })
                LaunchedEffect(state.current, state.next) {
                    if (state.current != MediaItem.EMPTY && state.next != MediaItem.EMPTY) {
                        if (firstTime) {
                            exoPlayer.setMediaItem(state.current)
                            exoPlayer.prepare()
                            exoPlayer.play()
                            firstTime = false
                        } else {
                            animationEnabled = true
                            delay(500)
                            exoPlayer.setMediaItem(state.next)
                            exoPlayer.prepare()
                            exoPlayer.play()
                        }
                    }
                }
                AndroidView(factory = {
                    PlayerView(it).apply {
                        player = exoPlayer
                        useController = false
                        layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    }
                }, modifier = Modifier.fillMaxWidth())
                Box(modifier = Modifier
                    .background(Color.Black.copy(animationAlpha))
                    .zIndex(1f)
                    .fillMaxSize())
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .zIndex(2f)
                        .align(Alignment.BottomCenter)
                        .padding(20.dp),
                    state = state.nextButton,
                    title = stringResource(id = R.string.next),
                    useComponent = useComponent
                )
            }
        }
    }
}

