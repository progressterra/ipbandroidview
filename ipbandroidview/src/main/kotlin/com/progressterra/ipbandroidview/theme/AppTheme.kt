package com.progressterra.ipbandroidview.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

object IpbTheme {

    val colors
        @Composable @ReadOnlyComposable get() = LocalColors.current

    val typography
        @Composable @ReadOnlyComposable get() = LocalTypography.current
}

val LocalTypography = staticCompositionLocalOf { IpbTypography() }

val LocalColors = staticCompositionLocalOf { IpbColors() }

@Composable
fun AppTheme(
    lightColors: IpbColors = defaultLightColors,
    darkColors: IpbColors = defaultDarkColors,
    typography: IpbTypography = IpbTheme.typography,
    content: @Composable () -> Unit
) {
    val colors = if (isSystemInDarkTheme()) darkColors else lightColors
    CompositionLocalProvider(
        LocalTypography provides typography,
        LocalColors provides colors
    ) {
        SystemUiSetup()
        content()
    }
}