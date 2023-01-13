package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.model.partner.Partner
import com.progressterra.ipbandroidview.theme.AppTheme

private val blockHeight = 130.dp

@Composable
fun TestPartnerBlock(
    modifier: Modifier = Modifier,
    partner: Partner,
    onPartnerClick: () -> Unit
) {
    BottomHolder(
        modifier = modifier
            .padding(horizontal = AppTheme.dimensions.small)
            .height(blockHeight)
    ) {
        SimpleImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(AppTheme.shapes.small)
                .niceClickable(onClick = onPartnerClick),
            url = partner.miniImageUrl,
            backgroundColor = AppTheme.colors.surfaces
        )
    }
}