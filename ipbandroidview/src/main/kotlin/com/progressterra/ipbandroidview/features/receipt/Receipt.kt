package com.progressterra.ipbandroidview.features.receipt

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
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedDivider
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.linktext.LinkText
import com.progressterra.ipbandroidview.shared.ui.linktext.LinkTextData

@Composable
fun Receipt(
    modifier: Modifier = Modifier,
    state: ReceiptState,
    useComponent: UseReceipt
) {

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .background(IpbTheme.colors.surface.asBrush()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BrushedText(
                text = stringResource(R.string.total_to_pay),
                tint = IpbTheme.colors.textPrimary.asBrush(),
                style = IpbTheme.typography.title
            )
            BrushedText(
                text = state.total.toString(),
                tint = IpbTheme.colors.textPrimary.asBrush(),
                style = IpbTheme.typography.title
            )
        }
        BrushedDivider(
            tint = IpbTheme.colors.background.asColor(),
            thickness = 1.dp
        )
        state.items.forEach {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BrushedText(
                    text = "${it.name} x${it.quantity}",
                    tint = IpbTheme.colors.textTertiary.asBrush(),
                    style = IpbTheme.typography.caption
                )
                BrushedText(
                    text = it.price.toString(),
                    tint = if (it.price.isNegative()) IpbTheme.colors.error.asBrush() else IpbTheme.colors.textSecondary.asBrush(),
                    style = IpbTheme.typography.caption
                )
            }
        }
        BrushedDivider(
            modifier = Modifier.padding(horizontal = 12.dp),
            tint = IpbTheme.colors.background.asColor(),
            thickness = 1.dp
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            state = state.pay,
            useComponent = useComponent,
            title = stringResource(R.string.pay)
        )
        LinkText(
            linkTextData = listOf(
                LinkTextData(text = stringResource(id = R.string.payment_0)), LinkTextData(
                    text = stringResource(id = R.string.user_agreement),
                    url = stringResource(id = R.string.user_agreement_url),
                ), LinkTextData(text = stringResource(id = R.string.and)), LinkTextData(
                    text = stringResource(id = R.string.payment_agreement),
                    url = stringResource(id = R.string.payment_agreement_url),
                ), LinkTextData(text = stringResource(id = R.string.payment_1)), LinkTextData(
                    text = stringResource(id = R.string.service_name),
                    url = stringResource(id = R.string.service_url),
                )
            ), useComponent = useComponent
        )
    }
}