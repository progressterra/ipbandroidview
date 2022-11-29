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
import com.progressterra.ipbandroidview.composable.component.TransparentTopAppBar
import com.progressterra.ipbandroidview.composable.element.BackIcon
import com.progressterra.ipbandroidview.composable.element.SimpleImage
import com.progressterra.ipbandroidview.composable.element.TrashIcon
import com.progressterra.ipbandroidview.theme.AppTheme
import com.skydoves.landscapist.ImageOptions

@Composable
fun PhotoScreen(
    state: () -> PhotoState,
    back: () -> Unit,
    remove: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.black),
        contentAlignment = Alignment.Center
    ) {
        TransparentTopAppBar(modifier = Modifier.align(Alignment.TopCenter), leftActions = {
            IconButton(onClick = back) {
                BackIcon()
            }
        }, rightActions = {
            if (state().enabled) IconButton(onClick = remove) {
                TrashIcon(enabled = { true })
            }
        })
        state().picture?.let {
            SimpleImage(
                modifier = Modifier.fillMaxWidth(),
                url = it::fullSize,
                options = ImageOptions(contentScale = ContentScale.FillWidth),
                backgroundColor = AppTheme.colors.black
            )
        }
    }
}

@Preview
@Composable
private fun PhotoViewerPreview() {
    AppTheme {
    }
}