package com.progressterra.ipbandroidview.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

object IpbTheme {

    val colors
        @Composable @ReadOnlyComposable get() = LocalColors.current

    val typography
        @Composable @ReadOnlyComposable get() = LocalTypography.current

    val dimensions
        @Composable @ReadOnlyComposable get() = LocalDimensions.current

    val shapes
        @Composable @ReadOnlyComposable get() = LocalShapes.current
}

val LocalIpbTypography = staticCompositionLocalOf { IpbTypography() }

val LocalIpbColors = staticCompositionLocalOf { defaultLightColors }

@Composable
fun IpbTheme(
    lightColors: Colors = defaultLightColors,
    darkColors: Colors = defaultDarkColors,
    typography: AppTypography = AppTheme.typography,
    dimensions: Dimensions = AppTheme.dimensions,
    content: @Composable () -> Unit
) {
    val customCursorColors = TextSelectionColors(
        handleColor = AppTheme.colors.primary,
        backgroundColor = AppTheme.colors.primary.copy(alpha = 0.4f)
    )
    val colors = if (isSystemInDarkTheme()) darkColors else lightColors
    CompositionLocalProvider(
        LocalDimensions provides dimensions,
        LocalTypography provides typography,
        LocalColors provides colors,
        LocalTextSelectionColors provides customCursorColors
    ) {
        SystemUiSetup()
        content()
    }
}