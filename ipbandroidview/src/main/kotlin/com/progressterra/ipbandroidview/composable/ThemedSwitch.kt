package com.progressterra.ipbandroidview.composable

import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun ThemedSwitch(
    modifier: Modifier = Modifier,
    onChange: (Boolean) -> Unit,
    checked: Boolean,
    enabled: Boolean = true
) {
    Switch(
        modifier = modifier,
        checked = checked,
        enabled = enabled,
        onCheckedChange = onChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = IpbTheme.colors.primary,
            checkedTrackColor = IpbTheme.colors.background,
            uncheckedThumbColor = IpbTheme.colors.gray2,
            uncheckedTrackColor = IpbTheme.colors.background,
            disabledCheckedThumbColor = IpbTheme.colors.primary,
            disabledCheckedTrackColor = IpbTheme.colors.background,
            disabledUncheckedThumbColor = IpbTheme.colors.gray2,
            disabledUncheckedTrackColor = IpbTheme.colors.background
        )
    )
}