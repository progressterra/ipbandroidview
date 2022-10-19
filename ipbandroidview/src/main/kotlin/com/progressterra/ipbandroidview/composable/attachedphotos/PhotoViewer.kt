package com.progressterra.ipbandroidview.composable.attachedphotos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.TransparentTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun PhotoViewer(
    modifier: Modifier = Modifier,
    painter: Painter,
    onBack: () -> Unit,
    onRemove: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.black), contentAlignment = Alignment.Center
    ) {
        TransparentTopAppBar(modifier = Modifier.align(Alignment.TopCenter), onBack = {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = stringResource(id = R.string.navigate_back),
                    tint = AppTheme.colors.surfaces
                )
            }
        }, actions = {
            IconButton(onClick = onRemove) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_trash),
                    contentDescription = stringResource(id = R.string.trash),
                    tint = AppTheme.colors.error
                )
            }
        })
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painter,
            contentDescription = stringResource(id = R.string.image),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Preview
@Composable
private fun PhotoViewerPreview() {
    AppTheme {
        PhotoViewer(
            painter = painterResource(id = R.drawable.splash_logo),
            onBack = { },
            onRemove = { })
    }
}