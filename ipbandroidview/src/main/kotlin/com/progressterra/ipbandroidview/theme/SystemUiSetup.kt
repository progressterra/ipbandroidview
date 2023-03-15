package com.progressterra.ipbandroidview.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SystemUiSetup() {
    val systemUiController = rememberSystemUiController()
    val systemBarColor = IpbTheme.colors.primary.asColor()
    val transparentColor: (Color) -> Color = { original ->
        systemBarColor.compositeOver(original)
    }
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = systemBarColor,
            darkIcons = true,
            transformColorForLightContent = transparentColor
        )
        systemUiController.setNavigationBarColor(
            color = systemBarColor,
            darkIcons = true,
            navigationBarContrastEnforced = false,
            transformColorForLightContent = transparentColor
        )
    }
}