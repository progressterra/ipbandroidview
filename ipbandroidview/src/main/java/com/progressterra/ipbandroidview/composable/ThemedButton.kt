package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.AppTheme

private val roundingCorner = 14.dp
private val verticalPadding = 15.dp
private val horizontalPadding = 32.dp

@Composable
fun ThemedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    enabled: Boolean = true
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(roundingCorner),
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = if (enabled) AppTheme.colors.primary else AppTheme.colors.gray3,
            contentColor = AppTheme.colors.surfaces,
            disabledContentColor = AppTheme.colors.gray2
        ),
        contentPadding = PaddingValues(horizontal = horizontalPadding, vertical = verticalPadding)
    ) {
        Text(text = text, style = AppTheme.typography.button)
    }
}

@Preview
@Composable
fun ThemedButtonEnabledPreview() {
    AppTheme {
        ThemedButton(onClick = {}, text = "Some button")
    }
}

@Preview
@Composable
fun ThemedButtonDisabledPreview() {
    AppTheme {
        ThemedButton(onClick = {}, text = "Some button", enabled = false)
    }
}