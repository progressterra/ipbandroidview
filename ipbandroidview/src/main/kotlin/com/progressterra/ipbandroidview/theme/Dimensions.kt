package com.progressterra.ipbandroidview.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimensions(
    /**
     * 2 dp by default
     */
    val tiniest: Dp = 2.dp,
    /**
     * 4 dp by default
     */
    val tiny: Dp = 4.dp,
    /**
     * 6 dp by default smany = tiny + small
     */
    val smany: Dp = 6.dp,
    /**
     * 8 dp by default
     */
    val small: Dp = 8.dp,
    /**
     * 12 dp by default
     */
    val medium: Dp = 12.dp,
    /**
     * 16 dp by default
     */
    val large: Dp = 16.dp,
    /**
     * 15 dp by default
     */
    val buttonVerticalPadding: Dp = 15.dp,
    /**
     * 32 dp by default
     */
    val buttonHorizontalPadding: Dp = 32.dp,
    /**
     * 16 dp by default
     */
    val counterSize: Dp = 16.dp,
)

