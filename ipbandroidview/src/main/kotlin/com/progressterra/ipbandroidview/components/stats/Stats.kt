package com.progressterra.ipbandroidview.components.stats

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun Stats(modifier: Modifier = Modifier, stats: ChecklistStats) {

    @Composable
    fun Item(icon: Painter, title: String, tint: Color) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(tint = tint, painter = icon, contentDescription = title)
            Spacer(modifier = Modifier.size(8.dp))
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
            icon = painterResource(id = R.drawable.ic_sum),
            title = stats.total.toString(),
            tint = AppTheme.colors.gray1
        )
        Item(
            icon = painterResource(id = R.drawable.ic_plus),
            title = stats.successful.toString(),
            tint = AppTheme.colors.primary
        )
        Item(
            icon = painterResource(id = R.drawable.ic_minus),
            title = stats.failed.toString(),
            tint = AppTheme.colors.error
        )
        Item(
            icon = painterResource(id = R.drawable.ic_remaining),
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