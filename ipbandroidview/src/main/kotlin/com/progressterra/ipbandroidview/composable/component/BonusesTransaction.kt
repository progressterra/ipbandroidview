package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.progressterra.ipbandroidview.composable.element.BonusesSmallIcon
import com.progressterra.ipbandroidview.model.bonuses.Transaction
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun BonusesTransaction(modifier: Modifier = Modifier, state: () -> Transaction) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .padding(vertical = AppTheme.dimensions.small, horizontal = AppTheme.dimensions.medium),
        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiniest)) {
            Text(
                text = state().date,
                style = AppTheme.typography.actionBarLabels,
                color = AppTheme.colors.gray2
            )
            Text(
                text = state().name,
                style = AppTheme.typography.text,
                color = AppTheme.colors.gray1
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (state().delta >= 0)
                Text(
                    text = "+${state().delta}",
                    style = AppTheme.typography.text,
                    color = AppTheme.colors.primary
                )
            else
                Text(
                    text = state().delta.toString(),
                    style = AppTheme.typography.text,
                    color = AppTheme.colors.error
                )
            BonusesSmallIcon()
        }
    }
}