package com.progressterra.ipbandroidview.shared.ui.modifier

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.niceClickable(enabled: Boolean = true, onClick: () -> Unit) = composed {
    clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = rememberRipple(),
        onClick = onClick,
        enabled = enabled
    )
}
