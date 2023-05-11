package com.progressterra.ipbandroidview.composable

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

private val paddingVertical = 32.dp

@Composable
fun BonusesWidget(
    modifier: Modifier = Modifier, bonuses: String
) {
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
                text = bonuses,
                color = AppTheme.colors.primary,
                style = AppTheme.typography.headLine
            )
            BonusesLargeIcon()
        }
        Text(
            text = "= $bonuses ${stringResource(R.string.currency)}",
            color = AppTheme.colors.gray2,
            style = AppTheme.typography.text
        )
    }
}

@Preview
@Composable
private fun BonusesWidgetPreview() {
    BonusesWidget(bonuses = "10")
}