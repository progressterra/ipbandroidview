package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.ProshkaBonusesState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Immutable
interface BonusSwitchState {

    val availableBonuses: ProshkaBonusesState

    val useBonuses: Boolean
}

@Composable
fun BonusSwitch(
    modifier: Modifier = Modifier, state: BonusSwitchState, switchUseBonuses: (Boolean) -> Unit
) {
    Column(
        modifier = modifier
            .clip(IpbTheme.shapes.medium)
            .background(IpbTheme.colors.surfaces)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(R.string.use_bonuses),
            color = IpbTheme.colors.black,
            style = IpbTheme.typography.title
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = state.availableBonuses.bonuses,
                color = IpbTheme.colors.primary,
                style = IpbTheme.typography.text
            )
            BonusesSmallIcon()
            Spacer(modifier = Modifier.weight(1f))
            ThemedSwitch(onChange = switchUseBonuses, checked = state.useBonuses)
        }
    }
}

private class BonusSwitchStatePreview(
    override val availableBonuses: ProshkaBonusesState = ProshkaBonusesState("100"),
    override val useBonuses: Boolean = true
) : BonusSwitchState

@Preview
@Composable
private fun BonusSwitchPreview() {
    IpbTheme {
        BonusSwitch(state = BonusSwitchStatePreview(), switchUseBonuses = {})
    }
}