package com.progressterra.ipbandroidview

import androidx.compose.runtime.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimensions(
    val small: Dp = 10.dp,
    val medium: Dp = 20.dp,
    val large: Dp = 40.dp
)

