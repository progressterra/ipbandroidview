package com.progressterra.ipbandroidview.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp

@Immutable
data class Shapes(
    val tiny: CornerBasedShape = RoundedCornerShape(6.dp),
    val small: CornerBasedShape = RoundedCornerShape(8.dp),
    val medium: CornerBasedShape = RoundedCornerShape(12.dp),
    val large: CornerBasedShape = RoundedCornerShape(20.dp),
    val button: CornerBasedShape = RoundedCornerShape(14.dp)
)
