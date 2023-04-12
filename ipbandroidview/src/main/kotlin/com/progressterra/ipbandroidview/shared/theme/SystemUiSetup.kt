package com.progressterra.ipbandroidview.shared.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SystemUiSetup() {
    val systemUiController = rememberSystemUiController()
    val systemBarColor = IpbTheme.colors.background.asColor()
    val transparentColor: (Color) -> Color = { original ->
        systemBarColor.compositeOver(original)
    }
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = true,
            transformColorForLightContent = transparentColor
        )
        systemUiController.setNavigationBarColor(
            color = Color.Transparent,
            darkIcons = true,
            navigationBarContrastEnforced = false,
            transformColorForLightContent = transparentColor
        )
    }
}