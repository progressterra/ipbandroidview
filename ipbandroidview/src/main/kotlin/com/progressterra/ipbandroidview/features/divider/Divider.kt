package com.progressterra.ipbandroidview.features.divider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText


@Composable
fun Divider(
    modifier: Modifier = Modifier, title: String
) {
    BoxWithConstraints {
        val width = maxWidth
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.widthIn(max = width - 32.dp)) {
                BrushedText(
                    textAlign = TextAlign.Start,
                    text = title,
                    style = IpbTheme.typography.caption2,
                    tint = IpbTheme.colors.textTertiary.asBrush()
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = if (title.isNotBlank()) 8.dp else 0.dp)
                    .background(IpbTheme.colors.textTertiary.asBrush())
                    .height(1.dp)
                    .weight(1f)
            )
        }
    }
}

@Preview
@Composable
private fun DividerPreview() {
    IpbTheme {
        Surface(color = IpbTheme.colors.surface.asColor()) {
            Divider(
                modifier = Modifier.fillMaxWidth(),
                title = "Some text"
            )
        }
    }
}

@Preview
@Composable
private fun CategoryDividerEmptyPreview() {
    IpbTheme {
        Surface(color = IpbTheme.colors.surface.asColor()) {
            Divider(
                title = ""
            )
        }
    }
}

@Preview
@Composable
private fun CategoryDividerLongPreview() {
    IpbTheme {
        Surface(color = IpbTheme.colors.surface.asColor()) {
            Divider(
                title = "Some very long text some very long text some very long text some very long text some very long text some very long text some very long text some very long text HAPPY END"
            )
        }
    }
}