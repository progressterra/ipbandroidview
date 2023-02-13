package com.progressterra.ipbandroidview.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class Colors(
    val primary: Color,
    val secondary: Color,
    val surfaces: Color,
    val black: Color,
    val gray1: Color,
    val gray2: Color,
    val gray3: Color,
    val background: Color,
    val error: Color,
    val success: Color,
    val failed: Color
)

val lightColors = Colors(
    primary = Color(0xFF364BB9),
    secondary = Color(0xFFFFCA61),
    surfaces = Color.White,
    black = Color(0xFF111111),
    gray1 = Color(0xFF3E4555),
    gray2 = Color(0xFF8D8D93),
    gray3 = Color(0xE0EDEDFC),
    background = Color(0xFFF0F0FF),
    error = Color(0xFFDF3636),
    success = Color(0xFFA0ECAC),
    failed = Color(0xFFF5B5B5)
)

val darkColors = Colors(
    primary = Color(0xFF364BB9),
    secondary = Color(0xFFFFCA61),
    surfaces = Color(0xFF343434),
    black = Color(0xFF111111),
    gray1 = Color(0xFF3E4555),
    gray2 = Color(0xFF8D8D93),
    gray3 = Color(0xE0EDEDFC),
    background = Color(0xFF101011),
    error = Color(0xFFDF3636),
    success = Color(0xFFA0ECAC),
    failed = Color(0xFFF5B5B5)
)