package com.progressterra.ipbandroidview.shared.ui.brushedswitch

import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun BrushedSwitch(
    modifier: Modifier = Modifier,
    state: BrushedSwitchState,
    useComponent: UseBrushedSwitch
) {
    Switch(
        modifier = modifier,
        checked = state.turned,
        enabled = state.enabled,
        onCheckedChange = { useComponent.handle(BrushedSwitchEvent.Click(state.id)) },
        colors = SwitchDefaults.colors(
            checkedThumbColor = IpbTheme.colors.primary.asColor(),
            checkedTrackColor = IpbTheme.colors.background.asColor(),
            uncheckedThumbColor = IpbTheme.colors.textDisabled.asColor(),
            uncheckedTrackColor = IpbTheme.colors.background.asColor(),
            disabledCheckedThumbColor = IpbTheme.colors.primary.asColor(),
            disabledCheckedTrackColor = IpbTheme.colors.background.asColor(),
            disabledUncheckedThumbColor = IpbTheme.colors.textDisabled.asColor(),
            disabledUncheckedTrackColor = IpbTheme.colors.background.asColor()
        )
    )
}