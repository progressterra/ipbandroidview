package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ThemedTextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: () -> String,
    enabled: () -> Boolean = { true }
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled(),
        colors = ButtonDefaults.textButtonColors(
            contentColor = AppTheme.colors.primary,
            disabledContentColor = AppTheme.colors.gray2
        ),
        contentPadding = PaddingValues(
            horizontal = AppTheme.dimensions.buttonHorizontalPadding,
            vertical = AppTheme.dimensions.buttonVerticalPadding
        )
    ) {
        Text(text = text(), style = AppTheme.typography.button)
    }
}

@Composable
fun ThemedTextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    enabled: () -> Boolean = { true }
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled(),
        colors = ButtonDefaults.textButtonColors(
            contentColor = AppTheme.colors.primary,
            disabledContentColor = AppTheme.colors.gray2
        ),
        contentPadding = PaddingValues(
            horizontal = AppTheme.dimensions.buttonHorizontalPadding,
            vertical = AppTheme.dimensions.buttonVerticalPadding
        )
    ) {
        Text(text = text, style = AppTheme.typography.button)
    }
}

@Preview
@Composable
private fun ThemedTextButtonEnabledPreview() {
    AppTheme {
        ThemedTextButton(onClick = {}, text = { "Some button" })
    }
}

@Preview
@Composable
private fun ThemedTextButtonDisabledPreview() {
    AppTheme {
        ThemedTextButton(onClick = {}, text = { "Some button" }, enabled = { false })
    }
}