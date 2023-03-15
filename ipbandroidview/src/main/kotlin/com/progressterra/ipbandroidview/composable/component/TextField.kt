package com.progressterra.ipbandroidview.composable.component

import android.os.Parcelable
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.composable.utils.clearFocusOnKeyboardDismiss
import com.progressterra.ipbandroidview.theme.IpbTheme
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class TextFieldState(
    val text: String = "", val hint: String = "", val enabled: Boolean = true
) : Parcelable {

    fun updateText(text: String): TextFieldState = copy(text = text)

    fun updateEnabled(enabled: Boolean): TextFieldState = copy(enabled = enabled)

    fun updateHint(hint: String): TextFieldState = copy(hint = hint)
}

sealed class TextFieldEvent {

    data class TextChanged(val text: String) : TextFieldEvent()

    object Action : TextFieldEvent()
}

interface UseTextField {

    fun handleEvent(id: String, event: TextFieldEvent)
}

private val borderWidth = 1.dp

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    id: String,
    state: TextFieldState,
    useComponent: UseTextField,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val label: (@Composable () -> Unit)? = if (state.text.isNotEmpty()) {
        {
            Text(
                text = state.hint, style = IpbTheme.typography.actionBarLabels, maxLines = 1
            )
        }
    } else null
    val placeholder: (@Composable () -> Unit)? = if (state.text.isEmpty()) {
        {
            Text(
                text = state.hint, style = IpbTheme.typography.text, maxLines = 1
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
                color = if (focused) IpbTheme.colors.primary else Color.Transparent,
                shape = IpbTheme.shapes.small
            )
            .clearFocusOnKeyboardDismiss(),
        value = state.text,
        visualTransformation = visualTransformation,
        interactionSource = mutableInteractionSource,
        onValueChange = { text ->
            useComponent.handleEvent(id, TextFieldEvent.TextChanged(text))
        },
        keyboardActions = KeyboardActions {
            focusManager.clearFocus()
            useComponent.handleEvent(id, TextFieldEvent.Action)
        },
        shape = IpbTheme.shapes.small,
        keyboardOptions = keyboardOptions,
        placeholder = placeholder,
        label = label,
        enabled = state.enabled,
        textStyle = IpbTheme.typography.text,
        singleLine = singleLine,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        colors = TextFieldDefaults.textFieldColors(
            //Background
            backgroundColor = IpbTheme.colors.background,
            //Placeholder
            placeholderColor = IpbTheme.colors.gray1,
            disabledPlaceholderColor = IpbTheme.colors.gray2,
            //Label always same color
            focusedLabelColor = IpbTheme.colors.gray2,
            unfocusedLabelColor = IpbTheme.colors.gray2,
            disabledLabelColor = IpbTheme.colors.gray2,
            errorLabelColor = IpbTheme.colors.error,
            //Text color depend on enable state
            textColor = IpbTheme.colors.black,
            disabledTextColor = IpbTheme.colors.gray2,
            //Here is no indicator actually
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            //Cursor
            cursorColor = IpbTheme.colors.primary,
            errorCursorColor = IpbTheme.colors.error,
            //Leading icon
            leadingIconColor = IpbTheme.colors.gray2,
            disabledLeadingIconColor = IpbTheme.colors.gray2,
            errorLeadingIconColor = IpbTheme.colors.error,
            //Leading icon
            trailingIconColor = IpbTheme.colors.gray2,
            disabledTrailingIconColor = IpbTheme.colors.gray2,
            errorTrailingIconColor = IpbTheme.colors.error
        )
    )
}
