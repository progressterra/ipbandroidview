package com.progressterra.ipbandroidview.shared.ui.switch

import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun Switch(
    modifier: Modifier = Modifier,
    state: SwitchState,
    useComponent: UseSwitch
) {
    Switch(
        modifier = modifier,
        checked = state.turned,
        enabled = state.enabled,
        onCheckedChange = { useComponent.handle(SwitchEvent(state.id)) },
        colors = SwitchDefaults.colors(
            checkedThumbColor = IpbTheme.colors.primary.asColor(),
            checkedTrackColor = IpbTheme.colors.secondary.asColor(),
            uncheckedThumbColor = IpbTheme.colors.textDisabled.asColor(),
            uncheckedTrackColor = IpbTheme.colors.secondary.asColor(),
            disabledCheckedThumbColor = IpbTheme.colors.primary.asColor(),
            disabledCheckedTrackColor = IpbTheme.colors.secondary.asColor(),
            disabledUncheckedThumbColor = IpbTheme.colors.textDisabled.asColor(),
            disabledUncheckedTrackColor = IpbTheme.colors.secondary.asColor()
        )
    )
}