package com.progressterra.ipbandroidview.pages.photo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.progressterra.ipbandroidview.features.phototopbar.PhotoTopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.skydoves.landscapist.ImageOptions

@Composable
fun PhotoScreen(
    state: PhotoState, useComponent: UsePhoto
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(IpbTheme.colors.background.asBrush()),
        contentAlignment = Alignment.Center
    ) {
        PhotoTopBar(modifier = Modifier.align(Alignment.TopCenter), useComponent = useComponent)
        state.picture?.let {
            SimpleImage(
                modifier = Modifier.fillMaxWidth(),
                image = it,
                options = ImageOptions(contentScale = ContentScale.FillWidth),
                backgroundColor = IpbTheme.colors.background.asColor()
            )
        }
    }
}