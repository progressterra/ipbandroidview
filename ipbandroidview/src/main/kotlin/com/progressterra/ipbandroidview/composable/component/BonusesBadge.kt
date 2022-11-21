package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.progressterra.ipbandroidview.composable.element.BonusesTinyIcon
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun BonusesBadge(modifier: Modifier = Modifier, bonuses: () -> Int, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(AppTheme.colors.primary)
            .niceClickable(onClick = onClick)
            .padding(horizontal = AppTheme.dimensions.medium, vertical = AppTheme.dimensions.smany),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiny)
    ) {
        Text(
            text = bonuses().toString(),
            color = AppTheme.colors.surfaces,
            style = AppTheme.typography.tertiaryText
        )
        BonusesTinyIcon()
    }
}