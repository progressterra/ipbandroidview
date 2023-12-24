package com.progressterra.ipbandroidview.features.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.ChecklistStats
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun Stats(modifier: Modifier = Modifier, stats: ChecklistStats, arrangement: Arrangement.Horizontal = Arrangement.SpaceEvenly) {

    @Composable
    fun Item(icon: @Composable () -> Unit, title: String, tint: Brush) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            icon()
            BrushedText(
                text = title,
                style = IpbTheme.typography.title2,
                tint = tint
            )
        }
    }

    Row(
        modifier = modifier,
        horizontalArrangement = arrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Item(
            icon = {
                BrushedIcon(
                    modifier = Modifier,
                    resId = R.drawable.ic_sum,
                    tint = IpbTheme.colors.iconTertiary.asBrush()
                )
            },
            title = stats.total.toString(),
            tint = IpbTheme.colors.textPrimary.asBrush()
        )
        Item(
            icon = {
                BrushedIcon(
                    modifier = Modifier,
                    resId = R.drawable.ic_plus,
                    tint = IpbTheme.colors.primary.asBrush()
                )
            },
            title = stats.successful.toString(),
            tint = IpbTheme.colors.primary.asBrush()
        )
        Item(
            icon = {
                BrushedIcon(
                    modifier = Modifier,
                    resId = R.drawable.ic_minus,
                    tint = IpbTheme.colors.error.asBrush()
                )
            },
            title = stats.failed.toString(),
            tint = IpbTheme.colors.error.asBrush()
        )
        Item(
            icon = {
                BrushedIcon(
                    modifier = Modifier,
                    resId = R.drawable.ic_remaining,
                    tint = IpbTheme.colors.iconTertiary.asBrush()
                )
            },
            title = stats.remaining.toString(),
            tint = IpbTheme.colors.textTertiary.asBrush()
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