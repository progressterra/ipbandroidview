package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.AppTheme
import com.progressterra.ipbandroidview.appColors
import com.progressterra.ipbandroidview.appTypography

private val verticalPadding = 15.dp
private val horizontalPadding = 32.dp

@Composable
fun ThemedTextButton(modifier: Modifier = Modifier, onClick: () -> Unit, text: String, enabled: Boolean = true) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.appColors.primary,
            disabledContentColor = MaterialTheme.appColors.gray2
        ),
        contentPadding = PaddingValues(horizontal = horizontalPadding, vertical = verticalPadding)
    ) {
        Text(text = text, style = MaterialTheme.appTypography.button)
    }
}

@Preview
@Composable
fun ThemedTextButtonEnabledPreview() {
    AppTheme {
        ThemedTextButton(onClick = {}, text = "Some button")
    }
}

@Preview
@Composable
fun ThemedTextButtonDisabledPreview() {
    AppTheme {
        ThemedTextButton(onClick = {}, text = "Some button", enabled = false)
    }
}