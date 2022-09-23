package com.progressterra.ipbandroidview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

object AppTheme {

    val colors
        @Composable @ReadOnlyComposable get() = LocalColors.current

    val typography
        @Composable @ReadOnlyComposable get() = LocalTypography.current

    val dimensions
        @Composable @ReadOnlyComposable get() = LocalDimensions.current
}

val LocalDimensions = staticCompositionLocalOf { AppDimensions() }

val LocalTypography = staticCompositionLocalOf { AppTypography() }

val LocalColors = staticCompositionLocalOf { AppColors() }


@Composable
fun AppTheme(
    colors: AppColors = AppTheme.colors,
    typography: AppTypography = AppTheme.typography,
    dimensions: AppDimensions = AppTheme.dimensions,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalDimensions provides dimensions,
        LocalTypography provides typography,
        LocalColors provides colors
    ) {
        SystemUiSetup()
        content()
    }
}