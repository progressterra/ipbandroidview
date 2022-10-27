package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun CategoryDivider(
    modifier: Modifier = Modifier, title: String = ""
) {
    BoxWithConstraints {
        val width = maxWidth
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.widthIn(max = width - 32.dp)) {
                Text(
                    textAlign = TextAlign.Start,
                    text = title,
                    style = AppTheme.typography.actionBarLabels,
                    color = AppTheme.colors.gray2
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = if (title.isNotBlank()) 8.dp else 0.dp)
                    .background(AppTheme.colors.gray2)
                    .height(1.dp)
                    .weight(1f)
            )
        }
    }
}

@Preview
@Composable
private fun CategoryDivider() {
    AppTheme {
        Surface(color = AppTheme.colors.surfaces) {
            CategoryDivider(
                modifier = Modifier.fillMaxWidth(),
                title = "Some text"
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
                title = ""
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
                title = "Some very long text some very long text some very long text some very long text some very long text some very long text some very long text some very long text HAPPY END"
            )
        }
    }
}