package com.progressterra.ipbandroidview.features.ordernumber

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.button.OutlineButton

@Composable
fun OrderNumber(
    modifier: Modifier = Modifier, state: OrderNumberState, useComponent: UseOrderNumber
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
    ) {
        BrushedText(
            modifier = Modifier,
            text = if (state.success) stringResource(R.string.success_payment) else stringResource(R.string.payment_error),
            style = IpbTheme.typography.title,
            tint = IpbTheme.colors.onBackground.asBrush()
        )
        BrushedText(
            modifier = Modifier,
            text = "${stringResource(R.string.order_id)} ${state.number}",
            style = IpbTheme.typography.subHeadlineRegular,
            tint = IpbTheme.colors.textTertiary.asBrush()
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BrushedText(
                modifier = Modifier,
                text = "${stringResource(R.string.goods_quantity)} ${state.quantity}",
                style = IpbTheme.typography.subHeadlineBold,
                tint = IpbTheme.colors.onBackground.asBrush()
            )
            BrushedText(
                modifier = Modifier,
                text = stringResource(R.string.delivery_address),
                style = IpbTheme.typography.footnoteBold,
                tint = IpbTheme.colors.textTertiary.asBrush()
            )
            BrushedText(
                modifier = Modifier,
                text = state.address,
                style = IpbTheme.typography.footnoteRegular,
                tint = IpbTheme.colors.textTertiary.asBrush()
            )
        }
        OutlineButton(
            modifier = Modifier.fillMaxWidth(),
            state = state.order,
            title = stringResource(id = R.string.to_order),
            useComponent = useComponent
        )
    }
}