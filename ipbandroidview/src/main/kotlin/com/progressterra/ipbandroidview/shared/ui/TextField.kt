package com.progressterra.ipbandroidview.shared.ui

import android.os.Parcelable
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.composable.utils.clearFocusOnKeyboardDismiss
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class TextFieldState(
    val id: String = "",
    val text: String = "",
    val hint: String = "",
    val enabled: Boolean = true
) : Parcelable {

    fun updateText(text: String): TextFieldState = copy(text = text)

    fun updateEnabled(enabled: Boolean): TextFieldState = copy(enabled = enabled)

    fun updateHint(hint: String): TextFieldState = copy(hint = hint)
}

sealed class TextFieldEvent {

    data class TextChanged(val text: String) : TextFieldEvent()

    object Action : TextFieldEvent()

    object AdditionalAction : TextFieldEvent()
}

interface UseTextField {

    fun handleEvent(id: String, event: TextFieldEvent)
}

private val borderWidth = 1.dp

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    useComponent: UseTextField,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true,
    actionIcon: Int? = null
) {
    val label: (@Composable () -> Unit)? = if (state.text.isNotEmpty()) {
        {
            BrushedText(
                text = state.hint, style = IpbTheme.typography.label,
                tint = IpbTheme.colors.textTertiary1.asBrush()
            )
        }
    } else null
    val placeholder: (@Composable () -> Unit)? = if (state.text.isEmpty()) {
        {
            BrushedText(
                text = state.hint, style = IpbTheme.typography.primary,
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
                width = borderWidth,
                brush = if (focused) IpbTheme.colors.primary.asBrush() else SolidColor(Color.Transparent),
                shape = RoundedCornerShape(8.dp)
            )
            .clearFocusOnKeyboardDismiss(),
        value = state.text,
        visualTransformation = visualTransformation,
        interactionSource = mutableInteractionSource,
        onValueChange = { text ->
            useComponent.handleEvent(state.id, TextFieldEvent.TextChanged(text))
        },
        keyboardActions = KeyboardActions {
            focusManager.clearFocus()
            useComponent.handleEvent(state.id, TextFieldEvent.Action)
        },
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = keyboardOptions,
        placeholder = placeholder,
        label = label,
        enabled = state.enabled,
        textStyle = IpbTheme.typography.primary,
        singleLine = singleLine,
        trailingIcon = {
            actionIcon?.let {
                IconButton(
                    onClick = {
                        useComponent.handleEvent(
                            state.id,
                            TextFieldEvent.AdditionalAction
                        )
                    }
                ) {
                    BrushedIcon(
                        resId = it,
                        tint = if (focused) IpbTheme.colors.primary.asBrush() else IpbTheme.colors.iconTertiary1.asBrush()
                    )
                }
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            //Background
            backgroundColor = IpbTheme.colors.surface1.asColor(),
            //Placeholder
            placeholderColor = IpbTheme.colors.textSecondary.asColor(),
            disabledPlaceholderColor = IpbTheme.colors.textDisabled.asColor(),
            //Label always same color
            focusedLabelColor = IpbTheme.colors.textTertiary1.asColor(),
            unfocusedLabelColor = IpbTheme.colors.textTertiary1.asColor(),
            disabledLabelColor = IpbTheme.colors.textTertiary1.asColor(),
            errorLabelColor = IpbTheme.colors.error.asColor(),
            //Text color depend on enable state
            textColor = IpbTheme.colors.textPrimary1.asColor(),
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
            leadingIconColor = if (focused) IpbTheme.colors.primary.asColor() else IpbTheme.colors.iconTertiary1.asColor(),
            disabledLeadingIconColor = IpbTheme.colors.iconDisabled1.asColor(),
            errorLeadingIconColor = IpbTheme.colors.error.asColor(),
            //Leading icon
            trailingIconColor = if (focused) IpbTheme.colors.primary.asColor() else IpbTheme.colors.iconTertiary1.asColor(),
            disabledTrailingIconColor = IpbTheme.colors.iconDisabled1.asColor(),
            errorTrailingIconColor = IpbTheme.colors.error.asColor()
        )
    )
}
