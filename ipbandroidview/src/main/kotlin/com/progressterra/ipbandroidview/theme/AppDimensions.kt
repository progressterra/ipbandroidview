package com.progressterra.ipbandroidview.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class AppDimensions(
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val regular: Dp = 12.dp,
    val large: Dp = 16.dp,
    val extraLarge: Dp = 20.dp
)

