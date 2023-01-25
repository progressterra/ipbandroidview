package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.model.partner.Partner
import com.progressterra.ipbandroidview.theme.AppTheme
import com.skydoves.landscapist.ImageOptions


@Composable
fun TestPartnerBlock(
    modifier: Modifier = Modifier,
    partner: Partner,
    onPartnerClick: () -> Unit
) {
    BottomHolder {
        SimpleImage(
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(2f)
                .clip(AppTheme.shapes.small)
                .niceClickable(onClick = onPartnerClick),
            url = partner.headImageUrl,
            backgroundColor = AppTheme.colors.surfaces,
            options = ImageOptions(contentScale = ContentScale.FillBounds)
        )
    }
}

@Composable
fun TestPartnerBlock2(
    modifier: Modifier = Modifier,
    partner: Partner,
    onPartnerClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clip(AppTheme.shapes.medium)
            .niceClickable(onClick = onPartnerClick)
            .background(AppTheme.colors.surfaces)
    ) {
        SimpleImage(
            modifier = Modifier.fillMaxSize(),
            url = partner.headImageUrl,
            backgroundColor = AppTheme.colors.surfaces
        )
    }
}