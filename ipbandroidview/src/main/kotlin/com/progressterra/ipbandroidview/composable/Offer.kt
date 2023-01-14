package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.model.partner.OfferUI
import com.progressterra.ipbandroidview.theme.AppTheme
import de.charlex.compose.HtmlText

private val cardWidth = 300.dp

private val cardHeight = 450.dp

@Composable
fun Offer(
    modifier: Modifier = Modifier,
    offerUI: OfferUI
) {
    Column(
        modifier = modifier
            .clip(AppTheme.shapes.small)
            .background(AppTheme.colors.surfaces)
            .size(height = cardHeight, width = cardWidth),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiny)
    ) {
        SimpleImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.33f),
            url = offerUI.imageUrl,
            backgroundColor = AppTheme.colors.surfaces
        )
        Text(
            text = offerUI.headDescription,
            style = AppTheme.typography.text,
            color = AppTheme.colors.black
        )
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            HtmlText(
                text = offerUI.fullDescription,
                style = AppTheme.typography.secondaryText,
                color = AppTheme.colors.black
            )
        }
    }
}