package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp

@Composable
fun ExactLinesText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle,
    color: Color,
    align: TextAlign,
    lines: Int
) {
    val lineHeight: Dp = with(LocalDensity.current) {
        (style.lineHeight * lines).toDp()
    }
    Box(modifier = modifier.height(lineHeight), contentAlignment = Alignment.Center) {
        Text(
            text = text, style = style, color = color, textAlign = align
        )
    }
}