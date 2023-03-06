package com.progressterra.ipbandroidview.composable.component

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import com.progressterra.ipbandroidview.theme.AppTheme

/**
 * @param modifier - modifier for TextField
 * @param text - text to display
 * @param hint - hint to display
 * @param onClick - callback on click
 */
@Composable
fun ThemedMimicField(
    modifier: Modifier = Modifier, text: String, hint: String, onClick: () -> Unit
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
        modifier = modifier
            .clip(AppTheme.shapes.small)
            .niceClickable { onClick() },
        value = text,
        onValueChange = {},
        shape = AppTheme.shapes.small,
        placeholder = placeholder,
        label = label,
        enabled = false,
        textStyle = AppTheme.typography.text,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            //Background
            backgroundColor = AppTheme.colors.background,
            //Placeholder
            placeholderColor = AppTheme.colors.gray1,
            disabledPlaceholderColor = AppTheme.colors.gray1,
            //Label always same color
            focusedLabelColor = AppTheme.colors.gray2,
            unfocusedLabelColor = AppTheme.colors.gray2,
            disabledLabelColor = AppTheme.colors.gray2,
            errorLabelColor = AppTheme.colors.error,
            //Text color depend on enable state
            textColor = AppTheme.colors.black,
            disabledTextColor = AppTheme.colors.black,
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

@Preview(showBackground = true)
@Composable
private fun ThemedMimicFieldPreviewEnabled() {
    AppTheme {
        ThemedMimicField(text = "Some text", hint = "Your name", onClick = {})
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF888888)
@Composable
private fun ThemedMimicFieldPreviewDisabled() {
    AppTheme {
        ThemedMimicField(text = "Some text", hint = "Your name", onClick = {})
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF888888)
@Composable
private fun ThemedMimicFieldPreviewEmptyDisabled() {
    AppTheme {
        ThemedMimicField(text = "", hint = "Your name", onClick = {})
    }
}