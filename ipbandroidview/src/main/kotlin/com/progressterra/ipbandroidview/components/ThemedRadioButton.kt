package com.progressterra.ipbandroidview.components

import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ThemedRadioButton(
    modifier: Modifier = Modifier,
    checked: () -> Boolean,
    enabled: () -> Boolean = { true },
    onClick: () -> Unit
) {
    RadioButton(
        modifier = modifier,
        selected = checked(),
        enabled = enabled(),
        onClick = onClick,
        colors = RadioButtonDefaults.colors(
            selectedColor = AppTheme.colors.primary, unselectedColor = AppTheme.colors.gray1
        )
    )
}