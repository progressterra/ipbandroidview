package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun CategoryDivider(
    modifier: Modifier = Modifier, gap: Dp = 8.dp, minDivWidth: Dp = 32.dp, text: String = ""
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier.weight(1f, false),
            text = text,
            style = AppTheme.typography.actionBarLabels,
            color = AppTheme.colors.gray2
        )
        if (text.isNotBlank()) Spacer(modifier = Modifier.size(gap))
        Box(
            modifier = Modifier
                .height(1.dp)
                .defaultMinSize(minWidth = minDivWidth)
                .background(AppTheme.colors.gray2)
        )
    }
}

@Preview
@Composable
private fun CategoryDivider() {
    AppTheme {
        Surface(color = AppTheme.colors.surfaces) {
            CategoryDivider(
                text = "Some category"
            )
        }
    }
}

@Preview
@Composable
private fun CategoryDividerEmpty() {
    AppTheme {
        Surface(color = AppTheme.colors.surfaces) {
            CategoryDivider(
                text = ""
            )
        }
    }
}

@Preview
@Composable
private fun CategoryDividerLong() {
    AppTheme {
        Surface(color = AppTheme.colors.surfaces) {
            CategoryDivider(
                text = "Some very long text some very long text some very long text some very long text some very long text some very long text some very long text some very long text HAPPY END"
            )
        }
    }
}