package com.progressterra.ipbandroidview.features.bonuses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState

@Composable
fun Bonuses(
    modifier: Modifier = Modifier,
    state: BonusesState,
    style: BonusesStyle = BonusesStyle.MAIN,
    useComponent: UseBonuses
) {
    StateColumn(
        modifier = modifier
            .fillMaxWidth()
            .height(162.dp),
        state = state.state,
        useComponent = useComponent
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(162.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(IpbTheme.colors.secondaryPressed.asBrush())
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BrushedText(
                    text = "${stringResource(R.string.you_have)} ${state.roubles} ${stringResource(R.string.roubles)}",
                    style = IpbTheme.typography.title,
                    tint = IpbTheme.colors.textButton.asBrush()
                )
                if (style == BonusesStyle.MAIN) {
                    IconButton(
                        modifier = Modifier.size(45.dp),
                        onClick = { useComponent.handle(BonusesEvent.Transactions) }) {
                        BrushedIcon(
                            modifier = Modifier.size(45.dp),
                            resId = R.drawable.ic_arrow,
                            tint = IpbTheme.colors.primary.asBrush(),
                        )
                    }
                }
            }
            if (style == BonusesStyle.MAIN && !state.hasCards) {
                BrushedText(
                    modifier = Modifier.niceClickable { useComponent.handle(BonusesEvent.AddCard) },
                    text = stringResource(R.string.add_card),
                    style = IpbTheme.typography.subHeadlineBold,
                    tint = IpbTheme.colors.primary.asBrush()
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BrushedText(
                    text = "${stringResource(R.string.can_be_out)} ${state.roubles} ${
                        stringResource(
                            R.string.roubles
                        )
                    }",
                    style = IpbTheme.typography.subHeadlineItalic,
                    tint = IpbTheme.colors.textTertiary.asBrush()
                )
                if (style == BonusesStyle.MAIN) {
                    IconButton(
                        modifier = Modifier.size(45.dp),
                        onClick = { useComponent.handle(BonusesEvent.Withdrawal) }) {
                        BrushedIcon(
                            modifier = Modifier.size(45.dp),
                            resId = R.drawable.ic_withdrawal,
                            tint = IpbTheme.colors.primary.asBrush(),
                        )
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                BrushedText(
                    text = stringResource(R.string.available_installment),
                    style = IpbTheme.typography.subHeadlineRegular,
                    tint = IpbTheme.colors.textSecondary.asBrush()
                )
                BrushedText(
                    text = stringResource(R.string.available_installment_2),
                    style = IpbTheme.typography.subHeadlineBold,
                    tint = IpbTheme.colors.textButton.asBrush()
                )
            }
        }
    }
}

@Composable
@Preview
private fun BonusesPreview0() {
    IpbTheme {
        Bonuses(
            state = BonusesState(
                roubles = "100",
                state = StateColumnState(state = ScreenState.SUCCESS)
            ), useComponent = UseBonuses.Empty()
        )
    }
}

@Composable
@Preview
private fun BonusesPreview1() {
    IpbTheme {
        Bonuses(
            state = BonusesState(
                roubles = "100",
                hasCards = true,
                state = StateColumnState(state = ScreenState.SUCCESS)
            ), useComponent = UseBonuses.Empty(),
            style = BonusesStyle.TRANSACTIONS
        )
    }
}

@Composable
@Preview
private fun BonusesPreview2() {
    IpbTheme {
        Bonuses(
            state = BonusesState(
                roubles = "100",
                hasCards = true,
                state = StateColumnState(state = ScreenState.SUCCESS)
            ), useComponent = UseBonuses.Empty()
        )
    }
}