package com.progressterra.ipbandroidview.features.bonustransaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun BonusTransaction(
    modifier: Modifier = Modifier,
    state: BonusTransactionState
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                val icon = when (state.type) {
                    BonusTransactionType.BUYING -> R.drawable.ic_buying_bonuses
                    BonusTransactionType.BURNING -> R.drawable.ic_burning_bonuses
                    BonusTransactionType.RECEIVING -> R.drawable.ic_receiving_bonuses
                }
                val iconColor = when (state.type) {
                    BonusTransactionType.BUYING -> IpbTheme.colors.iconTertiary3.asBrush()
                    BonusTransactionType.BURNING -> IpbTheme.colors.iconTertiary4.asBrush()
                    BonusTransactionType.RECEIVING -> IpbTheme.colors.iconTertiary2.asBrush()
                }
                val text = when (state.type) {
                    BonusTransactionType.BUYING -> R.string.bonus_transaction_buying
                    BonusTransactionType.BURNING -> R.string.bonus_transaction_burning
                    BonusTransactionType.RECEIVING -> R.string.bonus_transaction_receiving
                }
                BrushedIcon(
                    resId = icon,
                    tint = iconColor
                )
                BrushedText(
                    text = stringResource(text),
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                    style = IpbTheme.typography.body
                )
            }
            BrushedText(
                text = state.date,
                tint = IpbTheme.colors.textSecondary.asBrush(),
                style = IpbTheme.typography.subHeadlineRegular
            )
        }
        val color = when (state.type) {
            BonusTransactionType.BUYING -> IpbTheme.colors.textTertiary3.asBrush()
            BonusTransactionType.BURNING -> IpbTheme.colors.textTertiary4.asBrush()
            BonusTransactionType.RECEIVING -> IpbTheme.colors.textTertiary2.asBrush()
        }
        BrushedText(
            text = state.amount,
            tint = color,
            style = IpbTheme.typography.title
        )
    }
}