package com.progressterra.ipbandroidview

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SystemUiSetup() {
    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.surface
    val transparentColor: (Color) -> Color = { original ->
        systemBarColor.compositeOver(original)
    }
    SideEffect {
        systemUiController.setStatusBarColor(
            color = systemBarColor, darkIcons = true,
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