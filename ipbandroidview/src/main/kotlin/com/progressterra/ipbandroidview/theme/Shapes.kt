package com.progressterra.ipbandroidview.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp

@Immutable
data class Shapes(
    /**
     * Rounded with 6 dp by default
     */
    val tiny: CornerBasedShape = RoundedCornerShape(6.dp),
    /**
     * Rounded with 8 dp by default
     */
    val small: CornerBasedShape = RoundedCornerShape(8.dp),
    /**
     * Rounded with 12 dp by default
     */
    val medium: CornerBasedShape = RoundedCornerShape(12.dp),
    /**
     * Rounded with 20 dp by default
     */
    val large: CornerBasedShape = RoundedCornerShape(20.dp),
    /**
     * Rounded with 14 dp by default
     */
    val button: CornerBasedShape = RoundedCornerShape(14.dp),
    /**
     * Medium with 0 dp on bottom by default
     */
    val dialog: CornerBasedShape = medium.copy(
        bottomEnd = ZeroCornerSize, bottomStart = ZeroCornerSize
    )
)
