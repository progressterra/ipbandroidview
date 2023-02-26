package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.theme.AppTheme

data class TextButtonState(
    val text: String = "",
    val enabled: Boolean = true
) {
    fun updateText(text: String) = copy(text = text)
    fun updateEnabled(enabled: Boolean) = copy(enabled = enabled)
}

sealed class TextButtonEvent {

    object Click : TextButtonEvent()
}

interface UseTextButton {

    fun handleEvent(id: String, event: TextButtonEvent)
}

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    id: String,
    useComponent: UseTextButton,
    state: TextButtonState
) {
    TextButton(
        modifier = modifier,
        onClick = { useComponent.handleEvent(id, TextButtonEvent.Click) },
        enabled = state.enabled,
        colors = ButtonDefaults.textButtonColors(
            contentColor = AppTheme.colors.primary,
            disabledContentColor = AppTheme.colors.gray2
        ),
        contentPadding = PaddingValues(
            horizontal = AppTheme.dimensions.buttonHorizontalPadding,
            vertical = AppTheme.dimensions.buttonVerticalPadding
        )
    ) {
        Text(text = state.text, style = AppTheme.typography.button)
    }
}