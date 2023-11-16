package com.progressterra.ipbandroidview.features.ordercompact

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidapi.api.cart.models.TypeStatusOrder
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.entities.toString
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable

@Composable
fun OrderCompact(
    modifier: Modifier = Modifier, state: OrderCompactState, useComponent: UseOrderCompact
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .niceClickable { useComponent.handle(OrderCompactEvent(state)) }
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BrushedText(
                    text = "${stringResource(R.string.order_from)} ${state.date}",
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                    style = IpbTheme.typography.title
                )
            }
            BrushedText(
                text = state.number,
                tint = IpbTheme.colors.textTertiary.asBrush(),
                style = IpbTheme.typography.footnoteRegular
            )
            BrushedText(
                text = state.status.toString { stringResource(id = it) },
                tint = IpbTheme.colors.textPrimary.asBrush(),
                style = IpbTheme.typography.subHeadlineBold
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                BrushedText(
                    text = "${state.count} ${stringResource(R.string.sum_of_goods)} ",
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                    style = IpbTheme.typography.body
                )
                BrushedText(
                    text = state.totalPrice.toString(),
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                    style = IpbTheme.typography.body
                )
            }
        }
    }
}

@Composable
@Preview
private fun OrderDetailsPreview() {
    IpbTheme {
        OrderCompact(
            state = OrderCompactState(
                id = "dicam", number = "alienum", status = TypeStatusOrder.SENT_TO_WAREHOUSE,
                date = "13.10", count = 5, totalPrice = SimplePrice(price = 0),
            ), useComponent = UseOrderCompact.Empty()
        )
    }
}