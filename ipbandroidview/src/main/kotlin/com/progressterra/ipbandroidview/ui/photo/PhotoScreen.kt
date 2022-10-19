package com.progressterra.ipbandroidview.ui.photo

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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.TransparentTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun PhotoScreen(state: PhotoState, interactor: PhotoInteractor) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.black), contentAlignment = Alignment.Center
    ) {
        TransparentTopAppBar(modifier = Modifier.align(Alignment.TopCenter), onBack = {
            IconButton(onClick = { interactor.back() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = stringResource(id = R.string.navigate_back),
                    tint = AppTheme.colors.surfaces
                )
            }
        }, actions = {
            IconButton(onClick = { interactor.remove() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_trash),
                    contentDescription = stringResource(id = R.string.trash),
                    tint = AppTheme.colors.error
                )
            }
        })
        state.photo?.let {
            Image(
                modifier = Modifier.fillMaxWidth(),
                bitmap = it.asImageBitmap(),
                contentDescription = stringResource(id = R.string.image),
                contentScale = ContentScale.FillWidth
            )
        }

    }
}

@Preview
@Composable
private fun PhotoViewerPreview() {
    AppTheme {
        //TODO preview
    }
}