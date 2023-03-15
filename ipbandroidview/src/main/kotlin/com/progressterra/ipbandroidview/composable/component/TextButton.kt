package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.theme.IpbTheme

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
            contentColor = IpbTheme.colors.primary,
            disabledContentColor = IpbTheme.colors.gray2
        ),
        contentPadding = PaddingValues(
            horizontal = 32.dp,
            vertical = 15.dp
        )
    ) {
        Text(text = state.text, style = IpbTheme.typography.button)
    }
}