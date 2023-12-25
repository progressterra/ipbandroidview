package com.progressterra.ipbandroidview.shared.ui.modifier

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

@Suppress("unused")
fun Modifier.stealthClickable(
    enabled: Boolean = true, onClick: () -> Unit
) = composed {
    clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = onClick,
        enabled = enabled
    )
}