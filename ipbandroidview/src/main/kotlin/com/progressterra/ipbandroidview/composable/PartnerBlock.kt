package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.model.Partner
import com.progressterra.ipbandroidview.theme.AppTheme

private val height = 41.dp

private val width = 175.dp

sealed class PartnerBlockEvent {
    object PartnerClicked : PartnerBlockEvent()
}

@Composable
fun PartnerBlock(
    modifier: Modifier = Modifier, onEvent: (PartnerBlockEvent) -> Unit, partner: Partner
) {
    Row(
        modifier = modifier
            .niceClickable(onClick = { onEvent(PartnerBlockEvent.PartnerClicked) })
            .background(AppTheme.colors.surfaces)
            .padding(vertical = AppTheme.dimensions.small),
        horizontalArrangement = Arrangement.Center
    ) {
        SimpleImage(
            modifier = Modifier.size(width, height),
            url = partner.headImageUrl,
            backgroundColor = AppTheme.colors.surfaces
        )
    }
}