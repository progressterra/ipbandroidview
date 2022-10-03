package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme

private val roundingCornerSize = 8.dp

//TODO ime actions

@Composable
fun ThemedTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    onChange: ((String) -> Unit)? = null,
    onFocusChange: ((Boolean) -> Unit)? = null,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val label: (@Composable () -> Unit)? = if (text.isNotEmpty()) {
        {
            Text(
                text = hint, style = AppTheme.typography.actionBarLabels, maxLines = 1
            )
        }
    } else null
    val placeholder: (@Composable () -> Unit)? = if (text.isEmpty()) {
        {
            Text(
                text = hint, style = AppTheme.typography.text, maxLines = 1
            )
        }
    } else null
    var localText by remember(text) {
        mutableStateOf(text)
    }
    val mutableInteractionSource = remember { MutableInteractionSource() }
    val focused = mutableInteractionSource.collectIsFocusedAsState().value
    onFocusChange?.invoke(focused)
    TextField(
        modifier = modifier
            .border(
                width = 1.dp,
                color = if (focused) AppTheme.colors.primary else Color.Transparent,
                shape = RoundedCornerShape(roundingCornerSize)
            )
            .clearFocusOnKeyboardDismiss(),
        value = localText,
        interactionSource = mutableInteractionSource,
        onValueChange = {
            localText = it
            onChange?.invoke(it)
        },
        shape = RoundedCornerShape(roundingCornerSize),
        keyboardOptions = keyboardOptions,
        placeholder = placeholder,
        label = label,
        enabled = enabled,
        textStyle = AppTheme.typography.text,
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            placeholderColor = AppTheme.colors.gray1,
            disabledPlaceholderColor = AppTheme.colors.gray2,
            backgroundColor = AppTheme.colors.background,
            focusedLabelColor = AppTheme.colors.gray2,
            unfocusedLabelColor = AppTheme.colors.gray2,
            disabledLabelColor = AppTheme.colors.gray2,
            textColor = AppTheme.colors.black,
            disabledTextColor = AppTheme.colors.gray2,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            cursorColor = AppTheme.colors.primary
        )
    )
}

//TODO other colors

@Preview
@Composable
private fun ThemedTextFieldPreviewEnabled() {
    AppTheme {
        ThemedTextField(
            text = "Some text", hint = "Your name", enabled = true
        )
    }
}

@Preview
@Composable
private fun ThemedTextFieldPreviewDisabled() {
    AppTheme {
        ThemedTextField(
            text = "Some text", hint = "Your name", enabled = false
        )
    }
}

@Preview
@Composable
private fun ThemedTextFieldPreviewEmptyDisabled() {
    AppTheme {
        ThemedTextField(
            hint = "Your name", enabled = false
        )
    }
}