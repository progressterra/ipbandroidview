package com.progressterra.ipbandroidview.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

private fun String.toColor() = Color(android.graphics.Color.parseColor(this))

class ColorUnit(
    private vararg val colors: List<String>
)