package com.progressterra.ipbandroidview.shared.ui.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.progressterra.ipbandroidview.R
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
    singleLine: Boolean = true,
    backgroundColor: Color = IpbTheme.colors.surface.asColor(),
) {
    val label: (@Composable () -> Unit)? = if (state.text.isNotEmpty()) {
        {
            BrushedText(
                text = state.label ?: hint,
                style = IpbTheme.typography.caption,
                tint = IpbTheme.colors.textTertiary.asBrush()
            )
        }
    } else null
    val placeholder: (@Composable () -> Unit)? = if (state.text.isEmpty()) {
        {
            BrushedText(
                text = state.placeholder ?: hint,
                style = IpbTheme.typography.body,
                tint = IpbTheme.colors.textSecondary.asBrush()
            )
        }
    } else null
    val mutableInteractionSource = remember { MutableInteractionSource() }
    val focused = mutableInteractionSource.collectIsFocusedAsState().value
    val focusManager = LocalFocusManager.current
    val calendarState = rememberUseCaseState(visible = false)
    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            style = CalendarStyle.MONTH
        ),
        selection = CalendarSelection.Date {
            useComponent.handle(
                TextFieldEvent.TextChanged(
                    state.id,
                    "${it.dayOfMonth}${it.month}${it.year}"
                )
            )
        }
    )
    TextField(
        modifier = modifier
            .border(
                width = 1.dp,
                brush = if (focused) {
                    if (state.valid()) {
                        IpbTheme.colors.primary.asBrush()
                    } else {
                        IpbTheme.colors.error.asBrush()
                    }
                } else {
                    Color.Transparent.toBrush()
                },
                shape = RoundedCornerShape(8.dp)
            )
            .clearFocusOnKeyboardDismiss(),
        value = state.text,
        visualTransformation = state.type.toVisualTransformation(),
        interactionSource = mutableInteractionSource,
        onValueChange = { text ->
            if (text.length <= state.type.toAllowedChars()) {
                useComponent.handle(TextFieldEvent.TextChanged(state.id, text))
            }
        },
        keyboardActions = KeyboardActions {
            focusManager.clearFocus()
            useComponent.handle(TextFieldEvent.Action(state.id))
        },
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = state.type.toKeyboardOptions(),
        placeholder = placeholder,
        label = label,
        isError = !state.valid(),
        enabled = state.enabled,
        textStyle = IpbTheme.typography.body,
        singleLine = singleLine,
        trailingIcon = {
            val iconColor =
                if (focused) IpbTheme.colors.primary.asBrush() else IpbTheme.colors.iconTertiary.asBrush()
            IconButton(onClick = {
                when (state.type) {
                    TextInputType.DATE -> calendarState.show()
                    TextInputType.CHAT -> useComponent.handle(TextFieldEvent.AdditionalAction(state.id))
                    else -> useComponent.handle(TextFieldEvent.TextChanged(state.id, ""))
                }
            }) {
                BrushedIcon(
                    resId =
                    when (state.type) {
                        TextInputType.DATE -> R.drawable.ic_cal
                        TextInputType.CHAT -> R.drawable.ic_send
                        else -> R.drawable.ic_close
                    }, tint = iconColor
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            //Background
            backgroundColor = backgroundColor,
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