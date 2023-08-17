package com.progressterra.ipbandroidview.features.withdrawaltransaction

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
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.entities.toColor
import com.progressterra.ipbandroidview.entities.toString
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun WithdrawalTransaction(
    modifier: Modifier = Modifier, state: WithdrawalTransactionState
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BrushedText(
                text = state.sum.toString(),
                style = IpbTheme.typography.headline,
                tint = IpbTheme.colors.textPrimary.asBrush()
            )
            BrushedText(
                text = state.date,
                style = IpbTheme.typography.footnoteRegular,
                tint = IpbTheme.colors.textTertiary.asBrush()
            )
        }
        BrushedText(
            text = state.destination,
            style = IpbTheme.typography.subHeadlineRegular,
            tint = IpbTheme.colors.textPrimary.asBrush()
        )
        BrushedText(
            text = state.status.toString { stringResource(id = it) },
            style = IpbTheme.typography.footnoteRegular,
            tint = state.status.toColor()
        )
    }
}
