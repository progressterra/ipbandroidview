package com.progressterra.ipbandroidview.shared.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalTextApi::class)
@Composable
fun BrushedText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle,
    tint: Brush,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier, text = text, style = style.copy(
            brush = tint, textAlign = textAlign
        )
    )
}