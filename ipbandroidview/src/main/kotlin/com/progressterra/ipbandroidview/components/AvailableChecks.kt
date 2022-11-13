package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun AvailableChecks(
    modifier: Modifier = Modifier,
    count: () -> Int,
) {
    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(AppTheme.colors.error)
            .padding(horizontal = 6.dp, vertical = 2.dp)
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
        AvailableChecks(count = { 15 })
    }
}