package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.composable.utils.PrefixTransformation
import com.progressterra.ipbandroidview.composable.utils.clearFocusOnKeyboardDismiss
import com.progressterra.ipbandroidview.theme.AppTheme

private val borderWidth = 1.dp

@Composable
fun ThemedTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String,
    onChange: ((String) -> Unit)? = null,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    action: (() -> Unit)? = null,
    singleLine: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: String? = null
) {
    val label: (@Composable () -> Unit)? = if (text.isNotEmpty() || prefix != null) {
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
    val mutableInteractionSource = remember { MutableInteractionSource() }
    val focused = mutableInteractionSource.collectIsFocusedAsState().value
    val focusManager = LocalFocusManager.current
    TextField(
        modifier = modifier
            .border(
                width = borderWidth,
                color = if (focused) AppTheme.colors.primary else Color.Transparent,
                shape = AppTheme.shapes.small
            )
            .clearFocusOnKeyboardDismiss(),
        value = text,
        visualTransformation = prefix?.let { PrefixTransformation(it) }
            ?: VisualTransformation.None,
        interactionSource = mutableInteractionSource,
        onValueChange = { onChange?.invoke(it) },
        keyboardActions = KeyboardActions {
            focusManager.clearFocus()
            action?.invoke()
        },
        shape = AppTheme.shapes.small,
        keyboardOptions = keyboardOptions,
        placeholder = placeholder,
        label = label,
        enabled = enabled,
        textStyle = AppTheme.typography.text,
        singleLine = singleLine,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        colors = TextFieldDefaults.textFieldColors(
            //Background
            backgroundColor = AppTheme.colors.background,
            //Placeholder
            placeholderColor = AppTheme.colors.gray1,
            disabledPlaceholderColor = AppTheme.colors.gray2,
            //Label always same color
            focusedLabelColor = AppTheme.colors.gray2,
            unfocusedLabelColor = AppTheme.colors.gray2,
            disabledLabelColor = AppTheme.colors.gray2,
            errorLabelColor = AppTheme.colors.error,
            //Text color depend on enable state
            textColor = AppTheme.colors.black,
            disabledTextColor = AppTheme.colors.gray2,
            //Here is no indicator actually
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            //Cursor
            cursorColor = AppTheme.colors.primary,
            errorCursorColor = AppTheme.colors.error,
            //Leading icon
            leadingIconColor = AppTheme.colors.gray2,
            disabledLeadingIconColor = AppTheme.colors.gray2,
            errorLeadingIconColor = AppTheme.colors.error,
            //Leading icon
            trailingIconColor = AppTheme.colors.gray2,
            disabledTrailingIconColor = AppTheme.colors.gray2,
            errorTrailingIconColor = AppTheme.colors.error
        )
    )
}

@Preview
@Composable
private fun ThemedTextFieldPreviewEmptyDisabled() {
    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ThemedTextField(
                text = "Some text", hint = "Your name", enabled = true
            )
            ThemedTextField(
                text = "Some text", hint = "Your name", enabled = false
            )
            ThemedTextField(
                hint = "Your name", enabled = false, text = ""
            )
            ThemedTextField(
                hint = "Your name", enabled = true, prefix = "+7", text = ""
            )
        }
    }
}