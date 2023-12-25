package com.progressterra.ipbandroidview.shared.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun SimpleImage(
    modifier: Modifier = Modifier,
    image: String,
    options: ImageOptions = ImageOptions(),
    backgroundColor: Color
) {
    GlideImage(
        modifier = modifier,
        imageModel = { image },
        imageOptions = options,
        component = rememberImageComponent {
            +ShimmerPlugin(
                baseColor = backgroundColor, highlightColor = IpbTheme.colors.primary.asColor()
            )
        },
        failure = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                BrushedText(
                    text = stringResource(id = R.string.image_missing),
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                    style = IpbTheme.typography.body,
                    textAlign = TextAlign.Center
                )
            }
        },
        previewPlaceholder = R.drawable.dummy_200x400
    )
}