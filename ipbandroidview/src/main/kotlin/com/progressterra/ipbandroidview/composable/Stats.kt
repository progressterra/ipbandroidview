package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.model.checklist.ChecklistStats
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun Stats(modifier: Modifier = Modifier, stats: ChecklistStats) {

    @Composable
    fun Item(icon: @Composable () -> Unit, title: String, tint: Color) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
        ) {
            icon()
            Text(
                text = title,
                style = AppTheme.typography.text,
                color = tint
            )
        }
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Item(
            icon = { SumIcon() },
            title = stats.total.toString(),
            tint = AppTheme.colors.gray1
        )
        Item(
            icon = { SuccessIcon() },
            title = stats.successful.toString(),
            tint = AppTheme.colors.primary
        )
        Item(
            icon = { FailedIcon() },
            title = stats.failed.toString(),
            tint = AppTheme.colors.error
        )
        Item(
            icon = { RemainingIcon() },
            title = stats.remaining.toString(),
            tint = AppTheme.colors.gray2
        )
    }
}

@Preview
@Composable
private fun StatsPreview() {
    AppTheme {
        Stats(stats = ChecklistStats(14, 10, 3, 1))
    }
}