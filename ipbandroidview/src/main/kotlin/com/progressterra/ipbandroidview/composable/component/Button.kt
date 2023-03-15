package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.theme.IpbTheme

enum class ButtonStyle {
    Primary,
    Secondary,
    Error
}

data class ButtonState(
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
    id: String,
    modifier: Modifier = Modifier,
    state: ButtonState,
    style: ButtonStyle = ButtonStyle.Primary,
    useComponent: UseButton
) {
    Button(
        modifier = modifier,
        onClick = { useComponent.handleEvent(id, ButtonEvent.Click) },
        shape = IpbTheme.shapes.button,
        enabled = state.enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = when (style) {
                ButtonStyle.Primary -> IpbTheme.colors.primary
                ButtonStyle.Secondary -> IpbTheme.colors.secondary
                ButtonStyle.Error -> IpbTheme.colors.surfaces
            },
            contentColor = when (style) {
                ButtonStyle.Primary -> IpbTheme.colors.surfaces
                ButtonStyle.Secondary -> IpbTheme.colors.gray1
                ButtonStyle.Error -> IpbTheme.colors.error
            },
            disabledContentColor = IpbTheme.colors.gray2,
            disabledBackgroundColor = IpbTheme.colors.gray3
        ),
        contentPadding = PaddingValues(
            horizontal = 32.dp,
            vertical = 15.dp
        )
    ) {
        Text(text = state.text, style = IpbTheme.typography.button)
    }
}