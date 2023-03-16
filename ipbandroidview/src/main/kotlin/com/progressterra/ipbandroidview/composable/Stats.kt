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
import com.progressterra.ipbandroidview.model.ChecklistStats
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun Stats(modifier: Modifier = Modifier, stats: ChecklistStats) {

    @Composable
    fun Item(icon: @Composable () -> Unit, title: String, tint: Color) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            icon()
            Text(
                text = title,
                style = IpbTheme.typography.text,
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
            tint = IpbTheme.colors.gray1
        )
        Item(
            icon = { SuccessIcon() },
            title = stats.successful.toString(),
            tint = IpbTheme.colors.primary
        )
        Item(
            icon = { FailedIcon() },
            title = stats.failed.toString(),
            tint = IpbTheme.colors.error
        )
        Item(
            icon = { RemainingIcon() },
            title = stats.remaining.toString(),
            tint = IpbTheme.colors.gray2
        )
    }
}

@Preview
@Composable
private fun StatsPreview() {
    IpbTheme {
        Stats(stats = ChecklistStats(14, 10, 3, 1))
        Stats(stats = ChecklistStats(14, 10, 3, 3))
    }
}