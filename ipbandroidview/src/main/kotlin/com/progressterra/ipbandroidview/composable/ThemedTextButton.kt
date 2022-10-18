package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme

private val verticalPadding = 15.dp
private val horizontalPadding = 32.dp

@Composable
fun ThemedTextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    enabled: Boolean = true
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            contentColor = AppTheme.colors.primary, disabledContentColor = AppTheme.colors.gray2
        ),
        contentPadding = PaddingValues(horizontal = horizontalPadding, vertical = verticalPadding)
    ) {
        Text(text = text, style = AppTheme.typography.button)
    }
}

@Preview
@Composable
private fun ThemedTextButtonEnabledPreview() {
    AppTheme {
        ThemedTextButton(onClick = {}, text = "Some button")
    }
}

@Preview
@Composable
private fun ThemedTextButtonDisabledPreview() {
    AppTheme {
        ThemedTextButton(onClick = {}, text = "Some button", enabled = false)
    }
}