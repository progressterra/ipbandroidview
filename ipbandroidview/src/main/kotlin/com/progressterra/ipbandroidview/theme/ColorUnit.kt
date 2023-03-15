package com.progressterra.ipbandroidview.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor

class ColorUnit(
    vararg hexes: String
) {

    init {
        require(hexes.isNotEmpty()) { "ColorUnit must have at least 1 color" }
    }

    private val colors: List<Color> = hexes.map { it.toColor() }

    private val brush: Brush = if (hexes.size == 1) {
        SolidColor(colors.first())
    } else {
        Brush.verticalGradient(colors = colors)
    }

    fun asColor() = colors.first()

    fun asBrush() = brush
}