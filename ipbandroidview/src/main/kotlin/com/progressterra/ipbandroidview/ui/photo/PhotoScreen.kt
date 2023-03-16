package com.progressterra.ipbandroidview.ui.photo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.composable.BackIcon
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.composable.TransparentTopAppBar
import com.progressterra.ipbandroidview.composable.TrashIcon
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.skydoves.landscapist.ImageOptions

@Composable
fun PhotoScreen(
    state: PhotoState,
    interactor: PhotoInteractor
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(IpbTheme.colors.black),
        contentAlignment = Alignment.Center
    ) {
        TransparentTopAppBar(modifier = Modifier.align(Alignment.TopCenter), leftActions = {
            IconButton(onClick = { interactor.onBack() }) {
                BackIcon()
            }
        }, rightActions = {
            if (state.enabled) IconButton(onClick = { interactor.remove() }) {
                TrashIcon(enabled = true)
            }
        })
        state.picture?.let {
            SimpleImage(
                modifier = Modifier.fillMaxWidth(),
                url = it.fullSize,
                options = ImageOptions(contentScale = ContentScale.FillWidth),
                backgroundColor = IpbTheme.colors.black
            )
        }
    }
}

@Preview
@Composable
private fun PhotoViewerPreview() {
    IpbTheme {
    }
}