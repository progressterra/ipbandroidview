package com.progressterra.ipbandroidview

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalDimensions provides Dimensions(),
        LocalTypography provides AppTypography(),
        LocalColors provides AppColors()
    ) {
        MaterialTheme(
            content = content
        )
    }
}

val LocalDimensions = staticCompositionLocalOf { Dimensions() }

@Suppress("unused")
val MaterialTheme.dimensions: Dimensions
    @Composable
    @ReadOnlyComposable
    get() = LocalDimensions.current

val LocalTypography = staticCompositionLocalOf { AppTypography() }

@Suppress("unused")
val MaterialTheme.appTypography: AppTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalTypography.current

val LocalColors = staticCompositionLocalOf { AppColors() }

@Suppress("unused")
val MaterialTheme.appColors: AppColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current

@Suppress("unused")
val MaterialTheme.durations: Durations
    get() = Durations()
