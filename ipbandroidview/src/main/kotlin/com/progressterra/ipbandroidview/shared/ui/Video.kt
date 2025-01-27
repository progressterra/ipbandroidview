package com.progressterra.ipbandroidview.shared.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun Video(
    modifier: Modifier = Modifier,
    url: String,
    isPlayingExt: Boolean // Условие воспроизведения
) {
    var isPlaying by remember { mutableStateOf(false) }


    Box(modifier = modifier.size(192.dp, 108.dp)) {
        val context = LocalContext.current
        val exoPlayer = remember {
            ExoPlayer.Builder(context).build().apply {
                setMediaItem(MediaItem.fromUri(url))
                prepare()
            }
        }

        // Управляем воспроизведением
        LaunchedEffect(isPlaying, isPlayingExt) {
            if (isPlaying && isPlayingExt) {
                exoPlayer.play()
            } else {
                exoPlayer.pause()
            }
        }

        // Освобождаем ресурсы при выходе из жизненного цикла
        DisposableEffect(Unit) {
            onDispose {
                exoPlayer.release()
            }
        }

        // Видео
        AndroidView(
            factory = { context ->
                PlayerView(context).apply {
                    player = exoPlayer
                    useController = false // Отключить стандартные контролы
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        // Кнопка старт/стоп
        IconButton(
            onClick = { isPlaying = !isPlaying },
            modifier = Modifier
                .align(Alignment.Center)
                .size(48.dp)
        ) {
            Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = if (isPlaying) "Pause" else "Play",
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}


//@Composable
//fun Video(modifier: Modifier = Modifier, url: String) {
//    Box(modifier = modifier.size(192.dp, 108.dp)) {
//        val context = LocalContext.current
//        val exoPlayer = remember {
//            ExoPlayer.Builder(context).build().apply {
//                setMediaItem(MediaItem.fromUri(url))
//                prepare()
//
//            }
//        }
//
//        var isPlaying by remember { mutableStateOf(false) }
//
//        // Управляем воспроизведением
//        LaunchedEffect(isPlaying) {
//            if (isPlaying) {
//                exoPlayer.play()
//            } else {
//                exoPlayer.pause()
//            }
//        }
//
//        // Видео
//        AndroidView(
//            factory = { context ->
//                PlayerView(context).apply {
//                    player = exoPlayer
//                    useController = false // Отключить стандартные контролы
//                }
//            },
//            modifier = Modifier.fillMaxSize()
//        )
//
//        // Кнопка старт/стоп
//        IconButton(
//            onClick = { isPlaying = !isPlaying },
//            modifier = Modifier
//                .align(Alignment.Center)
//                .size(48.dp)
//        ) {
//            Icon(
//                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
//                contentDescription = if (isPlaying) "Pause" else "Play",
//                tint = MaterialTheme.colors.onSurface
//            )
//        }
//    }
//}

@Preview
@Composable
fun VideoPreview() {
    Video(
        modifier = Modifier,
        url = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
        isPlayingExt = true
    )
}
