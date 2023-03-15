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
import com.progressterra.ipbandroidview.model.OfferUI
import com.progressterra.ipbandroidview.theme.IpbTheme
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
            .clip(IpbTheme.shapes.small)
            .background(IpbTheme.colors.surfaces)
            .size(height = cardHeight, width = cardWidth),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        SimpleImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.33f),
            url = offerUI.imageUrl,
            backgroundColor = IpbTheme.colors.surfaces
        )
        Text(
            text = offerUI.headDescription,
            style = IpbTheme.typography.text,
            color = IpbTheme.colors.black
        )
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            HtmlText(
                text = offerUI.fullDescription,
                style = IpbTheme.typography.secondaryText,
                color = IpbTheme.colors.black
            )
        }
    }
}