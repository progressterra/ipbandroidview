package com.progressterra.ipbandroidview.composable

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme

private val roundingCornerSize = 8.dp

@Composable
fun ThemedTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String,
    onChange: (String) -> Unit,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val label: (@Composable () -> Unit)? = if (text.isNotEmpty()) {
        {
            Text(
                text = hint,
                color = AppTheme.colors.gray2,
                style = AppTheme.typography.actionBarLabels,
                maxLines = 1
            )
        }
    } else null
    val placeholder: (@Composable () -> Unit)? = if (text.isEmpty()) {
        {
            Text(
                text = hint,
                color = AppTheme.colors.gray1,
                style = AppTheme.typography.text,
                maxLines = 1
            )
        }
    } else null
    var focused by remember { mutableStateOf(false) }
    val resultModifier = if (focused) modifier.then(
        Modifier.border(
            width = 1.dp,
            color = AppTheme.colors.primary,
            shape = RoundedCornerShape(roundingCornerSize)
        )
    ) else modifier
    TextField(
        modifier = resultModifier
            .onFocusChanged {
                Log.d("FOCUS", it.isFocused.toString())
                focused = it.isFocused
            }
            .focusable(enabled),
        value = text,
        onValueChange = onChange,
        shape = RoundedCornerShape(roundingCornerSize),
        keyboardOptions = keyboardOptions,
        placeholder = placeholder,
        label = label,
        enabled = enabled,
        textStyle = AppTheme.typography.text,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            placeholderColor = AppTheme.colors.gray1,
            disabledPlaceholderColor = AppTheme.colors.gray1,
            backgroundColor = AppTheme.colors.background,
            focusedLabelColor = AppTheme.colors.gray2,
            unfocusedLabelColor = AppTheme.colors.gray2,
            disabledLabelColor = AppTheme.colors.gray2,
            textColor = AppTheme.colors.black,
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
fun ThemedTextFieldPreview0() {
    AppTheme {
        ThemedTextField(
            text = "Some text", hint = "Your name", onChange = { }, enabled = true
        )
    }
}

@Preview
@Composable
fun ThemedTextFieldPreview1() {
    AppTheme {
        ThemedTextField(
            text = "", hint = "Your name", onChange = { }, enabled = false
        )
    }
}