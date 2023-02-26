package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.theme.AppTheme

enum class ButtonStyle {
    Primary,
    Secondary,
    Error
}

data class ButtonState(
    val text: String = "",
    val enabled: Boolean = true
) {

    fun updateText(text: String): ButtonState = this.copy(text = text)

    fun updateEnabled(enabled: Boolean): ButtonState = this.copy(enabled = enabled)
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
        shape = AppTheme.shapes.button,
        enabled = state.enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = when (style) {
                ButtonStyle.Primary -> AppTheme.colors.primary
                ButtonStyle.Secondary -> AppTheme.colors.secondary
                ButtonStyle.Error -> AppTheme.colors.surfaces
            },
            contentColor = when (style) {
                ButtonStyle.Primary -> AppTheme.colors.surfaces
                ButtonStyle.Secondary -> AppTheme.colors.gray1
                ButtonStyle.Error -> AppTheme.colors.error
            },
            disabledContentColor = AppTheme.colors.gray2,
            disabledBackgroundColor = AppTheme.colors.gray3
        ),
        contentPadding = PaddingValues(
            horizontal = AppTheme.dimensions.buttonHorizontalPadding,
            vertical = AppTheme.dimensions.buttonVerticalPadding
        )
    ) {
        Text(text = state.text, style = AppTheme.typography.button)
    }
}