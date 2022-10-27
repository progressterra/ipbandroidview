package com.progressterra.ipbandroidview.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Roundings(
    val smallRounding: Dp = 8.dp,
    val mediumRounding: Dp = 12.dp,
    val largeRounding: Dp = 20.dp,
    val buttonRounding: Dp = 14.dp,
)

