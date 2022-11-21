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
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.composable.element.BonusesLargeIcon
import com.progressterra.ipbandroidview.model.SimplePrice
import com.progressterra.ipbandroidview.theme.AppTheme

private val paddingVertical = 32.dp

@Composable
fun BonusesWidget(modifier: Modifier, bonuses: () -> Int, bonusesValue: () -> SimplePrice) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .padding(vertical = paddingVertical),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiny)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = bonuses.toString(),
                color = AppTheme.colors.primary,
                style = AppTheme.typography.headLine
            )
            BonusesLargeIcon()
        }
        Text(
            text = "= ${bonusesValue().formattedPrice}",
            color = AppTheme.colors.gray2,
            style = AppTheme.typography.text
        )
    }
}