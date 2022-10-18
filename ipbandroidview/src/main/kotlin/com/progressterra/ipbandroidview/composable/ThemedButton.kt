package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ThemedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    enabled: Boolean = true,
    textColor: Color = AppTheme.colors.surfaces,
    tint: Color = AppTheme.colors.primary
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(AppTheme.dimensions.buttonRounding),
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = if (enabled) tint else AppTheme.colors.gray3,
            contentColor = textColor,
            disabledContentColor = AppTheme.colors.gray2
        ),
        contentPadding = PaddingValues(
            horizontal = 32.dp,
            vertical = 15.dp
        )
    ) {
        Text(text = text, style = AppTheme.typography.button)
    }
}

@Preview
@Composable
private fun ThemedButtonEnabledPreview() {
    AppTheme {
        ThemedButton(onClick = {}, text = "Some button")
    }
}

@Preview
@Composable
private fun ThemedButtonDisabledPreview() {
    AppTheme {
        ThemedButton(onClick = {}, text = "Some button", enabled = false)
    }
}