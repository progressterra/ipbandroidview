package com.progressterra.ipbandroidview.shared.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.itemgallery.ItemGalleryEvent
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun Image(modifier: Modifier = Modifier, image: String, options: ImageOptions = ImageOptions(), previewPlaceholder: Painter? = null) {

    if (image.endsWith(".mp4")) {
        Video(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                ,
            url = image,
            isPlayingExt = true
        )


    }
    else {
        GlideImage(
            modifier = modifier,
            imageModel = { image },
            imageOptions = options,
            component = rememberImageComponent { +ShimmerPlugin() },
            failure = {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = stringResource(id = R.string.image_missing),
                        tint = IpbTheme.colors.textPrimary.asBrush(),
                        style = IpbTheme.typography.body,
                        textAlign = TextAlign.Center
                    )
                }
            },

            previewPlaceholder = previewPlaceholder
                ?: painterResource(id = R.drawable.dummy_200x400)//painterResource(id = R.drawable.dummy_200x400)
        )
    }
}
