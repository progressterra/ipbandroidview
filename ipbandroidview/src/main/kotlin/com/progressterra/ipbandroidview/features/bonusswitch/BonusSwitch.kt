package com.progressterra.ipbandroidview.features.bonusswitch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitch

@Composable
fun BonusSwitch(
    modifier: Modifier = Modifier, state: BonusSwitchState, useComponent: UseBonusSwitch
) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        BrushedText(
            text = stringResource(R.string.use_bonuses),
            tint = IpbTheme.colors.textPrimary.asBrush(),
            style = IpbTheme.typography.title
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BrushedText(
                text = "${state.availableBonuses} ${stringResource(R.string.bonuses_short)}",
                tint = IpbTheme.colors.textTertiary.asBrush(),
                style = IpbTheme.typography.body
            )
            BrushedSwitch(
                state = state.useBonuses, useComponent = useComponent
            )
        }
    }
}

@Composable
@Preview
fun BonusSwitchPreview() {
    BonusSwitch(
        state = BonusSwitchState(availableBonuses = 1000),
        useComponent = UseBonusSwitch.Empty()
    )
}