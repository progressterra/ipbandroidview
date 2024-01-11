package com.progressterra.ipbandroidview.shared.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor

/**
 * Convert a color to a brush
 */
fun Color.toBrush() = SolidColor(this)

/**
 * Convert a hex string to a color
 */
fun String.toColor() = Color(android.graphics.Color.parseColor(this))
