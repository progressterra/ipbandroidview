package com.progressterra.ipbandroidview

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class AppDimensions(
    val small: Dp = 8.dp,
    val normal: Dp = 12.dp,
    val weighty: Dp = 16.dp,
    val medium: Dp = 20.dp,
)

