package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme

private val roundingCornerSize = 8.dp

@Composable
fun ThemedMimicField(
    modifier: Modifier = Modifier, text: String, hint: String
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
    TextField(
        modifier = modifier,
        value = text,
        onValueChange = {},
        shape = RoundedCornerShape(roundingCornerSize),
        placeholder = placeholder,
        label = label,
        enabled = false,
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
            disabledTextColor = AppTheme.colors.black,
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
private fun ThemedMimicFieldPreviewEnabled() {
    AppTheme {
        ThemedMimicField(
            text = "Some text", hint = "Your name"
        )
    }
}

@Preview
@Composable
private fun ThemedMimicFieldPreviewDisabled() {
    AppTheme {
        ThemedMimicField(
            text = "Some text", hint = "Your name"
        )
    }
}

@Preview
@Composable
private fun ThemedMimicFieldPreviewEmptyDisabled() {
    AppTheme {
        ThemedMimicField(
            text = "", hint = "Your name"
        )
    }
}