package com.progressterra.ipbandroidview.pages.videogallery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.SimpleVideo
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout

@Composable
fun VideoGalleryScreen(
    modifier: Modifier = Modifier,
    state: VideoGalleryState
) {
    ThemedLayout(
        modifier = modifier,
        topBar = {
            TopBar(title = stringResource(id = R.string.video_gallery), useComponent = object :
                UseTopBar {
                override fun handle(event: TopBarEvent) = Unit
            })
        }
    ) { _, _ ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            LazyRow(
                contentPadding = PaddingValues(20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(state.videos) { video ->
                    SimpleVideo(
                        url = video
                    )
                }

            }
        }
    }
}