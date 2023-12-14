package com.progressterra.ipbandroidview.shared.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun SimpleVideo(
    modifier: Modifier = Modifier,
    url: String
) {
    Box(modifier = modifier.size(192.dp, 108.dp)) {
        val context = LocalContext.current
        val exoPlayer = remember {
            ExoPlayer.Builder(context).build().apply {
                setMediaItem(MediaItem.fromUri(url))
                prepare()
            }
        }
        AndroidView(
            factory = {
                PlayerView(it).apply {
                    player = exoPlayer
                }
            },
            modifier = modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun SimpleVideoPreview() {
    SimpleVideo(
        modifier = Modifier,
        url = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
    )
}