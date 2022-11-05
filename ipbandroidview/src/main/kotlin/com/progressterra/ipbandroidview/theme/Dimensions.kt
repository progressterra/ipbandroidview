package com.progressterra.ipbandroidview.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimensions(
    /**
     * 2 dp by default
     */
    val tiny: Dp = 2.dp,
    /**
     * 4 dp by default
     */
    val small: Dp = 4.dp,
    /**
     * 8 dp by default
     */
    val medium: Dp = 8.dp,
    /**
     * 12 dp by default
     */
    val large: Dp = 12.dp,
    /**
     * 20 dp by default
     */
    val huge: Dp = 20.dp,
    /**
     * 64 dp by default
     */
    val gigantic: Dp = 64.dp
)

