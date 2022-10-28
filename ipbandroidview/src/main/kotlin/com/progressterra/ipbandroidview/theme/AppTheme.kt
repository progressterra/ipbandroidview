package com.progressterra.ipbandroidview.theme

import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

object AppTheme {

    val colors
        @Composable @ReadOnlyComposable get() = LocalColors.current

    val typography
        @Composable @ReadOnlyComposable get() = LocalTypography.current

    val roundings
        @Composable @ReadOnlyComposable get() = LocalRoundings.current

    val customization
        @Composable @ReadOnlyComposable get() = LocalCustomization.current
}

val LocalCustomization = staticCompositionLocalOf {
    Customization()
}

val LocalRoundings = staticCompositionLocalOf { Roundings() }

val LocalTypography = staticCompositionLocalOf { AppTypography() }

val LocalColors = staticCompositionLocalOf { AppColors() }

@Composable
fun AppTheme(
    colors: AppColors = AppTheme.colors,
    typography: AppTypography = AppTheme.typography,
    dimensions: Roundings = AppTheme.roundings,
    customization: Customization = AppTheme.customization,
    content: @Composable () -> Unit
) {
    val customCursorColors = TextSelectionColors(
        handleColor = AppTheme.colors.primary,
        backgroundColor = AppTheme.colors.primary.copy(alpha = 0.4f)
    )
    CompositionLocalProvider(
        LocalRoundings provides dimensions,
        LocalTypography provides typography,
        LocalColors provides colors,
        LocalCustomization provides customization,
        LocalTextSelectionColors provides customCursorColors
    ) {
        SystemUiSetup()
        content()
    }
}