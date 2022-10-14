package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun Percentage(
    modifier: Modifier = Modifier,
    percentage: Int = 0,
    successPercentage: Int = 85,
    verticalPadding: Dp = 2.dp,
    horizontalPadding: Dp = 4.dp
) {
    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(if (percentage >= successPercentage) AppTheme.colors.primary else AppTheme.colors.error)
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
    ) {
        Text(
            text = "$percentage%",
            color = AppTheme.colors.surfaces,
            style = AppTheme.typography.actionBarLabels
        )
    }
}

@Preview
@Composable
private fun PercentagePreviewFailed() {
    AppTheme {
        Percentage(percentage = 15)
    }
}

@Preview
@Composable
private fun PercentagePreviewSuccessful() {
    AppTheme {
        Percentage(percentage = 90)
    }
}