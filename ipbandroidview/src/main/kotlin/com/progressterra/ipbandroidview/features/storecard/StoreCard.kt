package com.progressterra.ipbandroidview.features.storecard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.progressterra.ipbandroidview.entities.Installment
import com.progressterra.ipbandroidview.entities.Price
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.theme.Preview
import com.progressterra.ipbandroidview.shared.ui.Icon
import com.progressterra.ipbandroidview.shared.ui.Text
import com.progressterra.ipbandroidview.shared.ui.Image
import com.progressterra.ipbandroidview.shared.ui.counter.Counter
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable

@Composable
fun StoreCard(
    modifier: Modifier = Modifier, state: StoreCardState, useComponent: UseStoreCard
) {
    Column(
        modifier = modifier
            .width(157.dp)
            .clip(RoundedCornerShape(8.dp))
            .niceClickable {
                useComponent.handle(StoreCardEvent.Open(state.id))
            }, verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Image(
            modifier = Modifier
                .size(width = 157.dp, height = 157.dp)
                .clip(RoundedCornerShape(8.dp)),
            image = state.image
        )
        Text(
            text = state.name,
            style = IpbTheme.typography.footnoteRegular,
            tint = IpbTheme.colors.textPrimary.asBrush(),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.width(if (state.counter.isEmpty()) 130.dp else 80.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = state.oldPrice.toString(),
                        style = IpbTheme.typography.body2,
                        tint = IpbTheme.colors.textTertiary.asBrush(),
                    )
                    Text(
                        text = stringResource(id = R.string.price_for_you),
                        style = IpbTheme.typography.footnoteRegular,
                        tint = IpbTheme.colors.textPrimary.asBrush(),
                    )
                    Text(
                        text = state.price.toString(),
                        style = IpbTheme.typography.subHeadlineRegular,
                        tint = IpbTheme.colors.textPrimary2.asBrush(),
                    )
                }
                if (!state.installment.isEmpty()) {
                    Text(
                        text = "(${stringResource(R.string.installment)}: ${
                            state.installment.months
                        } ${stringResource(R.string.payments)} ${stringResource(R.string.po)} ${state.installment.perMonth}",
                        style = IpbTheme.typography.footnoteRegular,
                        tint = IpbTheme.colors.textPrimary.asBrush(),
                    )
                }
            }
            if (state.counter.isEmpty()) {
                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = {
                        useComponent.handle(StoreCardEvent.AddToCart(state.id))
                    }) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        resId = R.drawable.ic_cart,
                        tint = IpbTheme.colors.iconPrimary.asBrush()
                    )
                }
            } else {
                Counter(
                    state = state.counter, useComponent = useComponent
                )
            }
        }
    }
}

@Preview
@Composable
private fun StoreCardPreview() {
    Preview {
        StoreCard(
            state = StoreCardState(
                name = "Ноутбук Lenovo IdeaPad 3 15ADA05",
                price = Price(1000)
            ), useComponent = UseStoreCard.Empty()
        )
        StoreCard(
            state = StoreCardState(
                name = "Ноутбук Lenovo IdeaPad 3 15ADA05",
                price = Price(1000),
                counter = CounterState("1", 5),
                installment = Installment(
                    months = 4,
                    perMonth = Price(500)
                )
            ), useComponent = UseStoreCard.Empty()
        )
    }
}