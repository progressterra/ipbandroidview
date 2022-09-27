package com.progressterra.ipbandroidview.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class AppDimensions(
    val pico: Dp = 2.dp,
    val nano: Dp = 4.dp,
    val micro: Dp = 6.dp,
    val milli: Dp = 8.dp,
    val normal: Dp = 12.dp,
    val kilo: Dp = 16.dp,
    val mega: Dp = 20.dp
)

