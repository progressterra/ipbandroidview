package com.progressterra.ipbandroidview.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Immutable
data class IpbColors(
    // Main
    val primary: Brush = Brush.verticalGradient(
        listOf(
            Color(0xFF35C290),
            Color(0xFF2E9399)
        )
    ),
    val secondary1: Brush = Color(0xFF3E4555).toBrush(),
    val secondary2: Brush = Color(0xFFCDCDD6).toBrush(),
    val secondary3: Brush = Brush.verticalGradient(
        listOf(
            Color(0xFFDCE8FF),
            Color(0xFFFFFFFF)
        )
    ),
    val tertiary: Brush = Color(0xFFB5B5BC).toBrush(),
    val background1: Brush = Color(0xFFF2F5FF).toBrush(),
    val background2: Brush = Color(0xFF202128).toBrush(),
    val onBackground1: Brush = Color(0xFF2E8E6C).toBrush(),
    val onBackground2: Brush = Color(0xFF1A1A20).toBrush(),
    val surface1: Brush = Color(0xFFFFFFFF).toBrush(),
    val surface2: Brush = Color(0xFF111111).toBrush(),
    val onSurface1: Brush = Color(0xFFFFFFFF).toBrush(),
    val onSurface2: Brush = Color(0xFF101010).toBrush(),
    val primaryPressed: Brush = Color(0xFF3D3D3D).toBrush(),
    val primaryDisabled: Brush = Color(0xFFB5B5B5).toBrush(),
    val secondaryPressed: Brush = Color(0xFF232427).toBrush(),
    // Status
    val error: Brush = Color(0xFFDF3636).toBrush(),
    val success: Brush = Color(0xFF7ADB6B).toBrush(),
    val info: Brush = Color(0xFF6980CF).toBrush(),
    val warning: Brush = Color(0xFFDB742A).toBrush(),
    // Text
    val textPrimary1: Brush = Color(0xFF111111).toBrush(),
    val textPrimary2: Brush = Color(0xFFE82741).toBrush(),
    val textPrimary3: Brush = Brush.verticalGradient(
        listOf(
            Color(0xFF35C290),
            Color(0xFF2E9399)
        )
    ),
    val textSecondary: Brush = Color(0xFF6E7289).toBrush(),
    val textTertiary1: Brush = Color(0xFF9191A1).toBrush(),
    val textTertiary2: Brush = Color(0xFF453896).toBrush(),
    val textTertiary3: Brush = Color(0xFF28AB13).toBrush(),
    val textTertiary4: Brush = Color(0xFFCA451C).toBrush(),
    val textButton: Brush = Color(0xFFFFFFFF).toBrush(),
    val textDisabled: Brush = Color(0xFFB5B5B5).toBrush(),
    val textPressed: Brush = Color(0xFF24282E).toBrush(),
    // Icons
    val iconPrimary1: Brush = Color(0xFF111111).toBrush(),
    val iconPrimary2: Brush = Color(0xFFE82741).toBrush(),
    val iconPrimary3: Brush = Brush.verticalGradient(
        listOf(
            Color(0xFF35C290),
            Color(0xFF2E9399)
        )
    ),
    val iconSecondary: Brush = Color(0xFFFFFFFF).toBrush(),
    val iconTertiary1: Brush = Color(0xFFB5B5BC).toBrush(),
    val iconTertiary2: Brush = Brush.verticalGradient(
        listOf(
            Color(0xFF4578DC),
            Color(0xFF453896)
        )
    ),
    val iconTertiary3: Brush = Brush.verticalGradient(
        listOf(
            Color(0xFFB2FF75),
            Color(0xFF28AB13)
        )
    ),
    val iconTertiary4: Brush = Brush.verticalGradient(
        listOf(
            Color(0xFFF6E651),
            Color(0xFFB80707)
        )
    ),
    val iconTertiary5: Brush = Color(0xFF1A1A20).toBrush(),
    val iconPressed: Brush = Color(0xFF0F1215).toBrush(),
    val iconDisabled1: Brush = Color(0xFFB5B5B5).toBrush(),
    val iconDisabled2: Brush = Color(0xFFEDF1FF).toBrush()
)

fun Color.toBrush() = Brush.verticalGradient(listOf(this, this))

val defaultLightColors = IpbColors()

val defaultDarkColors = IpbColors()