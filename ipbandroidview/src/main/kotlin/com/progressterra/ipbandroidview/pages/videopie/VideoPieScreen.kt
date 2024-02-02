package com.progressterra.ipbandroidview.pages.videopie

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.log
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn

@Composable
fun VideoPieScreen(
    modifier: Modifier, state: VideoPieScreenState, useComponent: UseVideoPieScreen
) {
    ThemedLayout(
        modifier = modifier
    ) { _, _ ->
        StateColumn(state = state.screen, useComponent = useComponent) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                var firstEnabled by remember { mutableStateOf(true) }
                val context = LocalContext.current
                var firstTime by remember { mutableStateOf(true) }
                val player1 = remember { ExoPlayer.Builder(context).build() }
                val player2 = remember { ExoPlayer.Builder(context).build() }
                LaunchedEffect(state.current, state.next) {
                    log("LaunchedEffect triggered")
                    if (state.current != MediaItem.EMPTY && state.next != MediaItem.EMPTY) {
                        if (firstTime) {
                            log("LaunchedEffect first time")
                            player1.setMediaItem(state.current)
                            player1.prepare()
                            player1.play()
                            player2.setMediaItem(state.next)
                            player2.prepare()
                            firstTime = false
                        } else {
                            log("LaunchedEffect not first time")
                            if (firstEnabled) {
                                log("was enabled 1, current: ${state.current}, next: ${state.next}")
                                firstEnabled = false
                                player1.stop()
                                player1.setMediaItem(state.next)
                                player1.prepare()
                                player2.play()
                            } else {
                                firstEnabled = true
                                log("was enabled 2, current: ${state.current}, next: ${state.next}")
                                player2.stop()
                                player2.setMediaItem(state.next)
                                player2.prepare()
                                player1.play()
                            }
                        }
                    }
                }


                androidx.compose.animation.AnimatedVisibility(
                    visible = !firstEnabled, enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 3000, easing = LinearEasing
                        )
                    ), exit = fadeOut(
                        animationSpec = tween(
                            durationMillis = 3000, easing = LinearEasing
                        )
                    ), label = "player2"
                ) {
                    AndroidView(
                        factory = {
                            PlayerView(it).apply {
                                player = player2
                                useController = false
                                layoutParams =
                                    FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                            }
                        }, modifier = Modifier.fillMaxWidth()
                    )
                }
                androidx.compose.animation.AnimatedVisibility(
                    visible = firstEnabled, enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 3000, easing = LinearEasing
                        )
                    ), exit = fadeOut(
                        animationSpec = tween(
                            durationMillis = 3000, easing = LinearEasing
                        )
                    ), label = "player1"
                ) {
                    AndroidView(
                        factory = {
                            PlayerView(it).apply {
                                player = player1
                                useController = false
                                layoutParams =
                                    FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                            }
                        }, modifier = Modifier.fillMaxWidth()
                    )
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
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

