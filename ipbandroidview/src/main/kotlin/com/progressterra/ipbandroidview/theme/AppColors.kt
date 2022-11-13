package com.progressterra.ipbandroidview.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class AppColors(
    val primary: Color = Color(0xFF364BB9),
    val secondary: Color = Color(0xFFFFCA61),
    val surfaces: Color = Color.White,
    val black: Color = Color(0xFF111111),
    val gray1: Color = Color(0xFF3E4555),
    val gray2: Color = Color(0xFF8D8D93),
    val gray3: Color = Color(0xE0EDEDFC),
    val background: Color = Color(0xFFF0F0FF),
    val error: Color = Color(0xFFDF3636),
    val success: Color = Color(0xFFA0ECAC),
    val failed: Color = Color(0xFFF5B5B5)
)
