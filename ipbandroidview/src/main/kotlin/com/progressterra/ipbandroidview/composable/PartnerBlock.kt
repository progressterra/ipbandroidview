package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import com.progressterra.ipbandroidview.model.Partner
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

private val height = 41.dp

private val width = 175.dp

sealed class PartnerBlockEvent {
    object PartnerClicked : PartnerBlockEvent()
}

@Composable
fun PartnerBlock(
    modifier: Modifier = Modifier, onEvent: (PartnerBlockEvent) -> Unit, partner: Partner
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .niceClickable { onEvent(PartnerBlockEvent.PartnerClicked) }
        .background(IpbTheme.colors.surfaces)
        .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center) {
        SimpleImage(
            modifier = Modifier.size(width, height),
            url = partner.headImageUrl,
            backgroundColor = IpbTheme.colors.surfaces
        )
    }
}

@Composable
fun PartnerBlock2(
    modifier: Modifier = Modifier, onEvent: (PartnerBlockEvent) -> Unit, partner: Partner
) {
    Row(modifier = modifier
        .clip(IpbTheme.shapes.medium)
        .niceClickable { onEvent(PartnerBlockEvent.PartnerClicked) }
        .background(IpbTheme.colors.surfaces)) {
        SimpleImage(
            modifier = Modifier.fillMaxSize(),
            url = partner.headImageUrl,
            backgroundColor = IpbTheme.colors.surfaces
        )
    }
}