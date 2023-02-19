package com.progressterra.ipbandroidview.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

object AppTheme {

    val colors
        @Composable @ReadOnlyComposable get() = LocalColors.current

    val typography
        @Composable @ReadOnlyComposable get() = LocalTypography.current

    val dimensions
        @Composable @ReadOnlyComposable get() = LocalDimensions.current

    val customization
        @Composable @ReadOnlyComposable get() = LocalCustomization.current

    val shapes
        @Composable @ReadOnlyComposable get() = LocalShapes.current

}

val LocalCustomization = staticCompositionLocalOf { Customization() }

val LocalDimensions = staticCompositionLocalOf { Dimensions() }

val LocalShapes = staticCompositionLocalOf { Shapes() }

val LocalTypography = staticCompositionLocalOf { AppTypography() }

val LocalColors = staticCompositionLocalOf { lightColors }

@Composable
fun AppTheme(
    accentColor: Color = Color(0xFF364BB9),
    typography: AppTypography = AppTheme.typography,
    dimensions: Dimensions = AppTheme.dimensions,
    customization: Customization = AppTheme.customization,
    content: @Composable () -> Unit
) {
    val customCursorColors = TextSelectionColors(
        handleColor = AppTheme.colors.primary,
        backgroundColor = AppTheme.colors.primary.copy(alpha = 0.4f)
    )
    val colors = if (isSystemInDarkTheme()) darkColors.copy(primary = accentColor) else lightColors.copy(primary = accentColor)
    CompositionLocalProvider(
        LocalDimensions provides dimensions,
        LocalTypography provides typography,
        LocalColors provides colors,
        LocalCustomization provides customization,
        LocalTextSelectionColors provides customCursorColors
    ) {
        SystemUiSetup()
        content()
    }
}