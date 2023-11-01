package com.progressterra.ipbandroidview.shared.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.progressterra.ipbandroidview.IpbAndroidViewSettings

object IpbTheme {

    val colors
        @Composable @ReadOnlyComposable get() = LocalColors.current

    val typography
        @Composable @ReadOnlyComposable get() = LocalTypography.current
}

val LocalTypography = staticCompositionLocalOf { IpbTypography() }

val LocalColors = staticCompositionLocalOf { IpbColors() }

@Composable
fun IpbTheme(
    typography: IpbTypography = IpbTheme.typography,
    content: @Composable () -> Unit
) {
    val colors =
        if (isSystemInDarkTheme()) IpbAndroidViewSettings.COLORS else IpbAndroidViewSettings.COLORS
    val selectionColors = TextSelectionColors(
        handleColor = IpbTheme.colors.primary.asColor(),
        backgroundColor = IpbTheme.colors.primary.asColor()
    )
    CompositionLocalProvider(
        LocalTypography provides typography,
        LocalColors provides colors,
        LocalTextSelectionColors provides selectionColors
    ) {
        SystemUiSetup()
        content()
    }
}