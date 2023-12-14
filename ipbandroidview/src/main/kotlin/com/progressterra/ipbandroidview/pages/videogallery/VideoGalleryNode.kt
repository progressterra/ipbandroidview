package com.progressterra.ipbandroidview.pages.videogallery

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node

@Suppress("unused")
class VideoGalleryNode(
    buildContext: BuildContext
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        VideoGalleryScreen(state = VideoGalleryState())
    }
}