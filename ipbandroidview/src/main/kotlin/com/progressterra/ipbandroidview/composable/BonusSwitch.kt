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
import com.progressterra.ipbandroidview.composable.component.BonusesComponentState
import com.progressterra.ipbandroidview.theme.AppTheme

@Immutable
interface BonusSwitchState {

    val availableBonuses: BonusesComponentState

    val useBonuses: Boolean
}

@Composable
fun BonusSwitch(
    modifier: Modifier = Modifier, state: BonusSwitchState, switchUseBonuses: (Boolean) -> Unit
) {
    Column(
        modifier = modifier
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .padding(AppTheme.dimensions.medium),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium)
    ) {
        Text(
            text = stringResource(R.string.use_bonuses),
            color = AppTheme.colors.black,
            style = AppTheme.typography.title
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = state.availableBonuses.bonuses,
                color = AppTheme.colors.primary,
                style = AppTheme.typography.text
            )
            BonusesSmallIcon()
            Spacer(modifier = Modifier.weight(1f))
            ThemedSwitch(onChange = switchUseBonuses, checked = state.useBonuses)
        }
    }
}

private class BonusSwitchStatePreview(
    override val availableBonuses: BonusesComponentState = BonusesComponentState("100"),
    override val useBonuses: Boolean = true
) : BonusSwitchState

@Preview
@Composable
fun BonusSwitchPreview() {
    AppTheme {
        BonusSwitch(state = BonusSwitchStatePreview(), switchUseBonuses = {})
    }
}