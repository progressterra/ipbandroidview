package com.progressterra.ipbandroidview.shared.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.fresco.FrescoImage
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun SimpleImage(
    modifier: Modifier = Modifier,
    url: String,
    options: ImageOptions = ImageOptions(),
    backgroundColor: Color
) {
    FrescoImage(
        modifier = modifier,
        imageUrl = url,
        imageOptions = options,
        component = rememberImageComponent {
            +ShimmerPlugin(
                baseColor = backgroundColor, highlightColor = IpbTheme.colors.secondary1.asColor()
            )
        },
        failure = {
            BrushedText(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.image_missing),
                tint = IpbTheme.colors.error.asBrush(),
                style = IpbTheme.typography.body,
                textAlign = TextAlign.Center
            )
        }, loading = {
            ThemedLoadingIndicator(modifier = Modifier.align(Alignment.Center))
        },
        previewPlaceholder = R.drawable.dummy_200x400
    )
}