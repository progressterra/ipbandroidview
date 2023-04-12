package com.progressterra.ipbandroidview.shared.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

object IpbTheme {

    val colors
        @Composable @ReadOnlyComposable get() = LocalColors.current

    val typography
        @Composable @ReadOnlyComposable get() = LocalTypography.current

    val customization
        @Composable @ReadOnlyComposable get() = LocalCustomization.current
}

val LocalCustomization = staticCompositionLocalOf { IpbCustomization() }

val LocalTypography = staticCompositionLocalOf { IpbTypography() }

val LocalColors = staticCompositionLocalOf { defaultIpbLightColors }

@Composable
fun IpbTheme(
    lightColors: IpbColors = defaultIpbLightColors,
    darkColors: IpbColors = defaultIpbDarkColors,
    typography: IpbTypography = IpbTheme.typography,
    customization: IpbCustomization = IpbTheme.customization,
    content: @Composable () -> Unit
) {
    val colors = if (isSystemInDarkTheme()) darkColors else lightColors
    CompositionLocalProvider(
        LocalTypography provides typography,
        LocalColors provides colors,
        LocalCustomization provides customization
    ) {
//        SystemUiSetup()
        content()
    }
}