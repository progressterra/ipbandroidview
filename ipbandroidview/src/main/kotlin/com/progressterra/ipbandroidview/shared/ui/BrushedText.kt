package com.progressterra.ipbandroidview.shared.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun BrushedText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle,
    tint: Brush,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        modifier = modifier,
        text = text,
        style = style.copy(
            brush = tint, textAlign = textAlign
        ),
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}