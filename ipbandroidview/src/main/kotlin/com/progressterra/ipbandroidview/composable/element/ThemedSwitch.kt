package com.progressterra.ipbandroidview.composable.element

import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ThemedSwitch(
    modifier: Modifier = Modifier,
    onChange: (Boolean) -> Unit,
    checked: () -> Boolean,
    enabled: () -> Boolean = { true }
) {
    Switch(
        modifier = modifier,
        checked = checked(),
        enabled = enabled(),
        onCheckedChange = onChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = AppTheme.colors.primary,
            checkedTrackColor = AppTheme.colors.background,
            uncheckedThumbColor = AppTheme.colors.gray2,
            uncheckedTrackColor = AppTheme.colors.background,
            disabledCheckedThumbColor = AppTheme.colors.primary,
            disabledCheckedTrackColor = AppTheme.colors.background,
            disabledUncheckedThumbColor = AppTheme.colors.gray2,
            disabledUncheckedTrackColor = AppTheme.colors.background
        )
    )
}