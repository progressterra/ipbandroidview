package com.progressterra.ipbandroidview.composable.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

//TODO check if there redundant lambdas
fun Modifier.niceClickable(
    enabled: Boolean = true, onClick: () -> Unit
) = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = rememberRipple(),
        onClick = onClick,
        enabled = enabled
    )
}