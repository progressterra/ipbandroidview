package com.progressterra.ipbandroidview.pages.videopie

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.progressterra.ipbandroidview.R
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
            Box(modifier = Modifier.fillMaxSize()) {
                val context = LocalContext.current
                var enabled1 by remember { mutableStateOf(true) }
                var firstTime by remember { mutableStateOf(true) }
                val alpha1 by animateFloatAsState(if (enabled1) 1f else 0f, label = "alpha1")
                val player1 = remember { ExoPlayer.Builder(context).build() }
                var enabled2 by remember { mutableStateOf(false) }
                val alpha2 by animateFloatAsState(if (enabled2) 1f else 0f, label = "alpha2")
                val player2 = remember { ExoPlayer.Builder(context).build() }
                LaunchedEffect(state.current, state.next) {
                    if (firstTime) {
                        player1.setMediaItem(state.current)
                        player1.prepare()
                        player1.play()
                        player2.setMediaItem(state.next)
                        player2.prepare()
                        firstTime = false
                    } else {
                        if (enabled1) {
                            enabled1 = false
                            enabled2 = true
                            player1.setMediaItem(state.next)
                            player1.prepare()
                            player2.play()
                        } else if (enabled2) {
                            enabled1 = true
                            enabled2 = false
                            player2.setMediaItem(state.next)
                            player2.prepare()
                            player1.play()
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .size(200.dp, 100.dp)
                        .align(Alignment.Center)
                        .alpha(alpha1)
                ) {
                    AndroidView(
                        factory = {
                            PlayerView(it).apply { player = player1 }
                        }, modifier = Modifier.fillMaxSize()
                    )
                }
                Box(
                    modifier = Modifier
                        .size(200.dp, 100.dp)
                        .align(Alignment.Center)
                        .alpha(alpha2)
                ) {
                    AndroidView(
                        factory = {
                            PlayerView(it).apply { player = player2 }
                        }, modifier = Modifier.fillMaxSize()
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

