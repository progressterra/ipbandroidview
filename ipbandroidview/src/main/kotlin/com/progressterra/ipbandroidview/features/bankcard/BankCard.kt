package com.progressterra.ipbandroidview.features.bankcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.toColor
import com.progressterra.ipbandroidview.entities.toString
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun Card(
    modifier: Modifier = Modifier,
    state: BankCardState,
    useComponent: UseBankCard
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .niceClickable(enabled = state.status != TypeStatusDoc.CONFIRMED) {
                useComponent.handleEvent(BankCardEvent.Click(state))
            }
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            if (state.isMainCard) {
                BrushedText(
                    text = stringResource(id = R.string.main_card),
                    style = IpbTheme.typography.subHeadlineBold,
                    tint = IpbTheme.colors.textPrimary.asBrush()
                )
                BrushedText(
                    text = state.name,
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
        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = { useComponent.handleEvent(BankCardEvent.Delete(state)) }) {
            BrushedIcon(
                resId = R.drawable.ic_trash, tint = IpbTheme.colors.iconTertiary.asBrush()
            )
        }
    }
}