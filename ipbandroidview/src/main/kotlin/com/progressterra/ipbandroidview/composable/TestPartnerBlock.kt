package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.model.Partner
import com.progressterra.ipbandroidview.theme.AppTheme

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