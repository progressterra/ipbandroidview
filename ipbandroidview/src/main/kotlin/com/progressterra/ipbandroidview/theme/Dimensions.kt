package com.progressterra.ipbandroidview.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimensions(
    val tiny: Dp = 2.dp,
    val small: Dp = 4.dp,
    val medium: Dp = 8.dp,
    val large: Dp = 12.dp,
    val huge: Dp = 20.dp,
    val gigantic: Dp = 64.dp
)

