package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.theme.AppTheme

//TODO: make button stateful and with interactor, maybe with an id

data class ButtonComponentState(
    val id: String,
    val enabled: Boolean
)

/**
 * @param modifier - modifier for the button
 * @param onClick - click listener for the button
 * @param text - text for the button
 * @param enabled - whether the button is enabled
 * @param textColor - text color for the button
 * @param tint - tint for the button
 */
@Composable
fun ButtonComponent(
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
        shape = AppTheme.shapes.button,
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = if (enabled) tint else AppTheme.colors.gray3,
            contentColor = textColor,
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
private fun ThemedButtonEnabledPreview() {
    AppTheme {
        ButtonComponent(onClick = {}, text = "Some button")
    }
}

@Preview
@Composable
private fun ThemedButtonDisabledPreview() {
    AppTheme {
        ButtonComponent(onClick = {}, text = "Some button", enabled = false)
    }
}