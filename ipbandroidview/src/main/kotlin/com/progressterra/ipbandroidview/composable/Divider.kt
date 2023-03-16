package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

private val lineWidth = 1.dp

private val lineLengthIfDividerNotEmpty = 0.dp

private val textRightMargin = 32.dp

@Composable
fun Divider(
    modifier: Modifier = Modifier, title: String
) {
    BoxWithConstraints {
        val width = maxWidth
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.widthIn(max = width - textRightMargin)) {
                Text(
                    textAlign = TextAlign.Start,
                    text = title,
                    style = IpbTheme.typography.actionBarLabels,
                    color = IpbTheme.colors.gray2
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = if (title.isNotBlank()) 8.dp else lineLengthIfDividerNotEmpty)
                    .background(IpbTheme.colors.gray2)
                    .height(lineWidth)
                    .weight(1f)
            )
        }
    }
}

@Preview
@Composable
private fun Divider() {
    IpbTheme {
        Surface(color = IpbTheme.colors.surfaces) {
            Divider(
                modifier = Modifier.fillMaxWidth(),
                title = "Some text"
            )
        }
    }
}

@Preview
@Composable
private fun CategoryDividerEmpty() {
    IpbTheme {
        Surface(color = IpbTheme.colors.surfaces) {
            Divider(
                title = ""
            )
        }
    }
}

@Preview
@Composable
private fun CategoryDividerLong() {
    IpbTheme {
        Surface(color = IpbTheme.colors.surfaces) {
            Divider(
                title = "Some very long text some very long text some very long text some very long text some very long text some very long text some very long text some very long text HAPPY END"
            )
        }
    }
}