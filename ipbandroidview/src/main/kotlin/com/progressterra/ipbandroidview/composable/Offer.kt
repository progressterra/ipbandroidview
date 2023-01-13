package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.progressterra.ipbandroidview.model.partner.OfferUI
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun Offer(
    modifier: Modifier = Modifier,
    offerUI: OfferUI
) {
    Column(
        modifier = modifier
            .clip(AppTheme.shapes.small)
            .background(AppTheme.colors.surfaces)
    ) {
        SimpleImage(
            url = offerUI.imageUrl,
            backgroundColor = AppTheme.colors.surfaces
        )
        Text(
            text = offerUI.headDescription,
            style = AppTheme.typography.text,
            color = AppTheme.colors.black
        )
        Text(
            text = offerUI.fullDescription,
            style = AppTheme.typography.secondaryText,
            color = AppTheme.colors.black
        )
    }
}