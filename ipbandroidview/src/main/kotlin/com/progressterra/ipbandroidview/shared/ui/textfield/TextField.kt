package com.progressterra.ipbandroidview.shared.ui.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
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
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.theme.toBrush
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.clearFocusOnKeyboardDismiss

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    useComponent: UseTextField,
    hint: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true,
    actionIcon: Int? = null
) {
    val label: (@Composable () -> Unit)? = if (state.text.isNotEmpty()) {
        {
            BrushedText(
                text = hint,
                style = IpbTheme.typography.caption,
                tint = IpbTheme.colors.textTertiary.asBrush()
            )
        }
    } else null
    val placeholder: (@Composable () -> Unit)? = if (state.text.isEmpty()) {
        {
            BrushedText(
                text = hint,
                style = IpbTheme.typography.body,
                tint = IpbTheme.colors.textSecondary.asBrush()
            )
        }
    } else null
    val mutableInteractionSource = remember { MutableInteractionSource() }
    val focused = mutableInteractionSource.collectIsFocusedAsState().value
    val focusManager = LocalFocusManager.current
    TextField(
        modifier = modifier
            .border(
                width = 1.dp,
                brush = if (focused) IpbTheme.colors.primary.asBrush() else Color.Transparent.toBrush(),
                shape = RoundedCornerShape(8.dp)
            )
            .clearFocusOnKeyboardDismiss(),
        value = state.text,
        visualTransformation = visualTransformation,
        interactionSource = mutableInteractionSource,
        onValueChange = { text ->
            useComponent.handle(TextFieldEvent.TextChanged(state.id, text))
        },
        keyboardActions = KeyboardActions {
            focusManager.clearFocus()
            useComponent.handle(TextFieldEvent.Action(state.id))
        },
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = keyboardOptions,
        placeholder = placeholder,
        label = label,
        enabled = state.enabled,
        textStyle = IpbTheme.typography.body,
        singleLine = singleLine,
        trailingIcon = actionIcon?.let {
            {
                IconButton(onClick = {
                    useComponent.handle(
                        TextFieldEvent.AdditionalAction(state.id)
                    )
                }) {
                    BrushedIcon(
                        resId = it,
                        tint = if (focused) IpbTheme.colors.iconPrimary3.asBrush() else IpbTheme.colors.iconTertiary.asBrush()
                    )
                }
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            //Background
            backgroundColor = IpbTheme.colors.surface.asColor(),
            //Placeholder
            placeholderColor = IpbTheme.colors.textSecondary.asColor(),
            disabledPlaceholderColor = IpbTheme.colors.textDisabled.asColor(),
            //Label always same color
            focusedLabelColor = IpbTheme.colors.textTertiary.asColor(),
            unfocusedLabelColor = IpbTheme.colors.textTertiary.asColor(),
            disabledLabelColor = IpbTheme.colors.textTertiary.asColor(),
            errorLabelColor = IpbTheme.colors.error.asColor(),
            //Text color depend on enable state
            textColor = IpbTheme.colors.textPrimary.asColor(),
            disabledTextColor = IpbTheme.colors.textDisabled.asColor(),
            //Here is no indicator actually
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            //Cursor
            cursorColor = IpbTheme.colors.primary.asColor(),
            errorCursorColor = IpbTheme.colors.error.asColor(),
            //Leading icon
            leadingIconColor = if (focused) IpbTheme.colors.primary.asColor() else IpbTheme.colors.iconTertiary.asColor(),
            disabledLeadingIconColor = IpbTheme.colors.iconDisabled.asColor(),
            errorLeadingIconColor = IpbTheme.colors.error.asColor(),
            //Leading icon
            trailingIconColor = if (focused) IpbTheme.colors.primary.asColor() else IpbTheme.colors.iconTertiary.asColor(),
            disabledTrailingIconColor = IpbTheme.colors.iconDisabled.asColor(),
            errorTrailingIconColor = IpbTheme.colors.error.asColor()
        )
    )
}

@Preview
@Composable
private fun TextFieldPreview() {
    IpbTheme {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            TextField(
                state = TextFieldState(
                    text = "123-450-30"
                ),
                useComponent = UseTextField.Empty(),
                hint = "Passport"
            )
            TextField(
                state = TextFieldState(),
                useComponent = UseTextField.Empty(),
                hint = "Passport"
            )
        }
    }
}