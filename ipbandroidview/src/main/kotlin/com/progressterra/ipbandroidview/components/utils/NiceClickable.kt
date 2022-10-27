package com.progressterra.ipbandroidview.components.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun Modifier.niceClickable(
    onClick: () -> Unit, enabled: Boolean = true
) = this.clickable(
    interactionSource = remember { MutableInteractionSource() },
    indication = rememberRipple(),
    onClick = onClick,
    enabled = enabled
)