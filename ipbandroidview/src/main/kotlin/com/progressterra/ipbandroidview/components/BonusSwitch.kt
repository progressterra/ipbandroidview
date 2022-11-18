package com.progressterra.ipbandroidview.components

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
import com.progressterra.ipbandroidview.model.component.AvailableBonuses
import com.progressterra.ipbandroidview.model.component.UseBonuses
import com.progressterra.ipbandroidview.theme.AppTheme

@Immutable
interface BonusSwitchState : AvailableBonuses, UseBonuses

@Composable
fun BonusSwitch(
    modifier: Modifier = Modifier,
    state: () -> BonusSwitchState,
    onChange: (Boolean) -> Unit,
    enabled: () -> Boolean = { true }
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
                text = state().bonuses.toString(),
                color = AppTheme.colors.primary,
                style = AppTheme.typography.text
            )
            BonusesIcon()
            Spacer(modifier = Modifier.weight(1f))
            ThemedSwitch(onChange = onChange, checked = state()::useBonuses, enabled = enabled)
        }
    }
}

private class BonusSwitchStatePreview : BonusSwitchState {

    override val bonuses: Int = 300
    override val useBonuses: Boolean = true
}

@Preview
@Composable
fun BonusSwitchPreview() {
    AppTheme {
        BonusSwitch(state = { BonusSwitchStatePreview() }, onChange = {}, enabled = { true })
    }
}