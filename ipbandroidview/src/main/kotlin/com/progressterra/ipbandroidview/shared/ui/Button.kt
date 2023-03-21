package com.progressterra.ipbandroidview.shared.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

data class ButtonState(
    val id: String = "",
    val text: String = "",
    val enabled: Boolean = true
) {

    fun updateText(text: String): ButtonState = copy(text = text)

    fun updateEnabled(enabled: Boolean): ButtonState = copy(enabled = enabled)
}

sealed class ButtonEvent {

    object Click : ButtonEvent()
}

interface UseButton {

    fun handleEvent(id: String, event: ButtonEvent)
}

/**
 * @param modifier - modifier for the button
 * @param state - state of the button
 * @param useComponent - use component for the button
 * @param style - style of the button
 * @param id - id of the button
 */
@Composable
fun Button(
    modifier: Modifier = Modifier,
    state: ButtonState,
    useComponent: UseButton
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(IpbTheme.colors.primary.asBrush())
            .padding(horizontal = 32.dp, vertical = 15.dp)
            .niceClickable { useComponent.handleEvent(state.id, ButtonEvent.Click) },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BrushedText(
            text = state.text,
            style = IpbTheme.typography.button,
            tint = IpbTheme.colors.textButton.asBrush()
        )
    }
}