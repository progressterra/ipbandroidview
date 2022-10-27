package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ThemedMimicField(
    modifier: Modifier = Modifier,
    roundingCornerSize: Dp = 8.dp,
    text: String,
    hint: String,
    onClick: () -> Unit
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
            .clip(RoundedCornerShape(roundingCornerSize))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = onClick
            ),
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
            disabledPlaceholderColor = AppTheme.colors.gray1,
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