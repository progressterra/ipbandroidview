package com.progressterra.ipbandroidview.features.proshkabonuses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun ProshkaBonuses(
    modifier: Modifier = Modifier,
    state: ProshkaBonusesState,
    style: ProshkaBonusesStyle = ProshkaBonusesStyle.NEXT,
    useComponent: UseProshkaBonuses
) {
    Box(modifier = modifier.padding(horizontal = 20.dp)) {
        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = { useComponent.handle(state.id, ProshkaBonusesEvent.Reverse) },
        ) {
            if (state.isReversed) {
                BrushedIcon(
                    resId = R.drawable.ic_money_bag_off,
                    tint = IpbTheme.colors.iconDisabled1.asBrush(),
                )
            } else {
                BrushedIcon(
                    resId = R.drawable.ic_money_bag,
                    tint = IpbTheme.colors.iconTertiary4.asBrush(),
                )
            }
        }
        IconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = { useComponent.handle(state.id, ProshkaBonusesEvent.Action) },
        ) {
            if (style == ProshkaBonusesStyle.NEXT) {
                BrushedIcon(
                    resId = R.drawable.ic_arrow_pro,
                    tint = IpbTheme.colors.iconPrimary3.asBrush(),
                )
            } else if (style == ProshkaBonusesStyle.PLUS) {
                BrushedIcon(
                    resId = R.drawable.ic_plus_pro,
                    tint = IpbTheme.colors.iconPrimary3.asBrush(),
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(IpbTheme.colors.secondaryPressed.asBrush())
                .padding(16.dp)
        ) {
            BrushedText(
                text = "${stringResource(R.string.you_have)} ${state.bonuses} ${stringResource(R.string.bonuses)}",
                style = IpbTheme.typography.title,
                tint = IpbTheme.colors.textButton.asBrush()
            )
            Spacer(modifier = Modifier.height(6.dp))
            BrushedText(
                text = "${stringResource(R.string.can_withdraw)} ${state.canWithdraw}",
                style = IpbTheme.typography.secondaryItalic,
                tint = IpbTheme.colors.textTertiary1.asBrush()
            )
            Spacer(modifier = Modifier.height(14.dp))
            BrushedText(
                text = stringResource(R.string.bonuses_rate),
                style = IpbTheme.typography.secondary,
                tint = IpbTheme.colors.textSecondary.asBrush()
            )
            Spacer(modifier = Modifier.height(30.dp))
            if (state.isReversed)
                Row {
                    BrushedText(
                        text = "${stringResource(R.string.available_loan)} - ",
                        style = IpbTheme.typography.secondary,
                        tint = IpbTheme.colors.textButton.asBrush()
                    )
                    BrushedText(
                        text = state.loan,
                        style = IpbTheme.typography.secondaryBold,
                        tint = IpbTheme.colors.textButton.asBrush()
                    )
                }
            else
                Row {
                    BrushedText(
                        text = state.burningDate,
                        style = IpbTheme.typography.secondary,
                        tint = IpbTheme.colors.textPrimary2.asBrush()
                    )
                    BrushedText(
                        text = "${stringResource(R.string.will_burn)} ${state.burningQuantity} ${
                            stringResource(
                                R.string.bonuses
                            )
                        }",
                        style = IpbTheme.typography.secondaryBold,
                        tint = IpbTheme.colors.textPrimary2.asBrush()
                    )
                }
        }
    }
}