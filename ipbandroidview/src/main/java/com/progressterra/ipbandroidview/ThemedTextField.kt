package com.progressterra.ipbandroidview

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ThemedTextField(
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
                color = MaterialTheme.appColors.gray2,
                style = MaterialTheme.appTypography.actionBarLabels,
                maxLines = 1
            )
        }
    } else null

    val placeholder: (@Composable () -> Unit)? = if (text.isEmpty()) {
        {
            Text(
                text = hint,
                color = MaterialTheme.appColors.gray1,
                style = MaterialTheme.appTypography.text,
                maxLines = 1
            )
        }
    } else null

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                //todo on focus line
            }, value = text, onValueChange = onChange,
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = keyboardOptions,
        placeholder = placeholder,
        label = label,
        enabled = enabled,
        textStyle = MaterialTheme.appTypography.text,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            placeholderColor = MaterialTheme.appColors.gray1,
            disabledPlaceholderColor = MaterialTheme.appColors.gray1,
            backgroundColor = MaterialTheme.appColors.background,
            focusedLabelColor = MaterialTheme.appColors.gray2,
            unfocusedLabelColor = MaterialTheme.appColors.gray2,
            disabledLabelColor = MaterialTheme.appColors.gray2,
            textColor = MaterialTheme.appColors.black,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun ThemedTextFieldPreview0() {
    AppTheme {
        ThemedTextField(
            text = "Some text",
            hint = "Your name",
            onChange = { },
            enabled = true
        )
    }
}

@Preview
@Composable
fun ThemedTextFieldPreview1() {
    AppTheme {
        ThemedTextField(
            text = "",
            hint = "Your name",
            onChange = { },
            enabled = false
        )
    }
}