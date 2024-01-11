package com.progressterra.ipbandroidview.shared.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * Class for color units. It can be a single color or a gradient (now only vertical)
 */
class ColorUnit(
    hexes: List<String>
) {
    init {
        require(hexes.isNotEmpty()) { "ColorUnit must have at least 1 color" }
    }

    private val colors: List<Color> = hexes.map { it.toColor() }

    private val brush: Brush = if (hexes.size == 1) {
        colors.first().toBrush()
    } else {
        Brush.verticalGradient(colors = colors)
    }

    fun asColor() = colors.first()

    fun asBrush() = brush
}