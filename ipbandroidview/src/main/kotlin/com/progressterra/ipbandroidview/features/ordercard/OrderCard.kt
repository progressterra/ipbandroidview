package com.progressterra.ipbandroidview.features.ordercard


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.theme.Preview
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun OrderCard(
    modifier: Modifier = Modifier, state: OrderCardState, useComponent: UseOrderCard
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .niceClickable {
                useComponent.handle(
                    OrderCardEvent(state.id)
                )
            }, horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SimpleImage(
            modifier = Modifier
                .size(157.dp)
                .clip(RoundedCornerShape(8.dp)),
            url = state.imageUrl,
            backgroundColor = IpbTheme.colors.background.asColor()
        )
        Column(
            modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            BrushedText(
                text = state.name,
                style = IpbTheme.typography.footnoteRegular,
                tint = IpbTheme.colors.textPrimary.asBrush(),
            )
            state.properties.forEach {
                BrushedText(
                    text = "${it.first}: ${it.second}",
                    style = IpbTheme.typography.footnoteRegular,
                    tint = IpbTheme.colors.textSecondary.asBrush()
                )
            }
            if (state.installment.isEmpty()) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    BrushedText(
                        text = state.oldPrice.toString(),
                        style = IpbTheme.typography.body2,
                        tint = IpbTheme.colors.textTertiary.asBrush(),
                    )
                    BrushedText(
                        text = stringResource(id = R.string.price_for_you),
                        style = IpbTheme.typography.footnoteRegular,
                        tint = IpbTheme.colors.textPrimary.asBrush(),
                    )
                    BrushedText(
                        text = state.price.toString(),
                        style = IpbTheme.typography.subHeadlineRegular,
                        tint = IpbTheme.colors.textPrimary2.asBrush(),
                    )
                }
            } else {
                BrushedText(
                    text = "${stringResource(R.string.po)} ${state.installment.perMonth} ${
                        state.installment.months
                    } ${stringResource(R.string.payments)}",
                    style = IpbTheme.typography.subHeadlineRegular,
                    tint = IpbTheme.colors.textPrimary2.asBrush(),
                )
                BrushedText(
                    text = "(${stringResource(R.string.installment)})",
                    style = IpbTheme.typography.subHeadlineRegular,
                    tint = IpbTheme.colors.textDisabled.asBrush(),
                )
            }
        }
    }
}

@Preview
@Composable
private fun CartCardPreview() {
    Preview {
        OrderCard(
            state = OrderCardState(
                name = "Ноутбук Lenovo IdeaPad 3 15ADA05",
                oldPrice = SimplePrice(10000),
                price = SimplePrice(1000)
            ), useComponent = UseOrderCard.Empty()
        )
    }
}