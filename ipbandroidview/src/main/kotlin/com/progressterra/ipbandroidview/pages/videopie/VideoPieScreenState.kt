package com.progressterra.ipbandroidview.pages.videopie

import androidx.compose.runtime.Immutable
import androidx.media3.common.MediaItem
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState

@Immutable
data class VideoPieScreenState(
    val current: MediaItem = MediaItem.EMPTY,
    val next: MediaItem = MediaItem.EMPTY,
    val nextButton: ButtonState = ButtonState(id = "next"),
    val downloaded: List<MediaItem> = emptyList(),
    val toDownload: List<String> = listOf(
        "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
        "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
        "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
        "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
        "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"
    ),
    val screen: StateColumnState = StateColumnState()
)