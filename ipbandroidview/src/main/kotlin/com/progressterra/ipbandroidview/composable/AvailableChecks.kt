package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun AvailableChecks(
    modifier: Modifier = Modifier,
    count: Int = 0,
    cornerRadius: Dp = 100.dp,
    verticalPadding: Dp = 2.dp,
    horizontalPadding: Dp = 6.dp
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(AppTheme.colors.error)
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
    ) {
        Text(
            text = count.toString(),
            color = AppTheme.colors.surfaces,
            style = AppTheme.typography.actionBarLabels
        )
    }
}

@Preview
@Composable
private fun AvailableChecksPreviewFailed() {
    AppTheme {
        AvailableChecks(count = 15)
    }
}