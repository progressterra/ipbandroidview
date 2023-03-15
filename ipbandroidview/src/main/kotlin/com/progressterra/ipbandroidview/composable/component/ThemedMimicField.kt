package com.progressterra.ipbandroidview.composable.component

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.theme.IpbTheme

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
                text = hint, style = IpbTheme.typography.actionBarLabels, maxLines = 1
            )
        }
    } else null
    val placeholder: (@Composable () -> Unit)? = if (text.isEmpty()) {
        {
            Text(
                text = hint, style = IpbTheme.typography.text, maxLines = 1
            )
        }
    } else null
    TextField(
        modifier = modifier
            .clip(IpbTheme.shapes.small)
            .niceClickable { onClick() },
        value = text,
        onValueChange = {},
        shape = IpbTheme.shapes.small,
        placeholder = placeholder,
        label = label,
        enabled = false,
        textStyle = IpbTheme.typography.text,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            //Background
            backgroundColor = IpbTheme.colors.background,
            //Placeholder
            placeholderColor = IpbTheme.colors.gray1,
            disabledPlaceholderColor = IpbTheme.colors.gray1,
            //Label always same color
            focusedLabelColor = IpbTheme.colors.gray2,
            unfocusedLabelColor = IpbTheme.colors.gray2,
            disabledLabelColor = IpbTheme.colors.gray2,
            errorLabelColor = IpbTheme.colors.error,
            //Text color depend on enable state
            textColor = IpbTheme.colors.black,
            disabledTextColor = IpbTheme.colors.black,
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

@Preview(showBackground = true)
@Composable
private fun ThemedMimicFieldPreviewEnabled() {
    IpbTheme {
        ThemedMimicField(text = "Some text", hint = "Your name", onClick = {})
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF888888)
@Composable
private fun ThemedMimicFieldPreviewDisabled() {
    IpbTheme {
        ThemedMimicField(text = "Some text", hint = "Your name", onClick = {})
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF888888)
@Composable
private fun ThemedMimicFieldPreviewEmptyDisabled() {
    IpbTheme {
        ThemedMimicField(text = "", hint = "Your name", onClick = {})
    }
}