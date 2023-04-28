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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.theme.Preview
import com.progressterra.ipbandroidview.shared.theme.ProjectType
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.counter.Counter
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState
import com.progressterra.ipbandroidview.shared.ui.niceClickable

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
        SimpleImage(
            modifier = Modifier
                .size(width = 157.dp, height = 157.dp)
                .clip(RoundedCornerShape(8.dp)),
            url = state.imageUrl,
            backgroundColor = IpbTheme.colors.background.asColor()
        )
        BrushedText(
            text = state.name,
            style = IpbTheme.typography.footnoteRegular,
            tint = IpbTheme.colors.textPrimary.asBrush(),
        )
        if (IpbTheme.customization.projectType == ProjectType.REDI) {
            BrushedText(
                text = state.company,
                style = IpbTheme.typography.footnoteRegular,
                tint = IpbTheme.colors.textTertiary.asBrush(),
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.width(80.dp), verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                BrushedText(
                    text = state.price.toString(),
                    style = IpbTheme.typography.subHeadlineRegular,
                    tint = IpbTheme.colors.textPrimary2.asBrush(),
                )
                if (IpbTheme.customization.projectType == ProjectType.REDI) {
                    BrushedText(
                        text = state.loan,
                        style = IpbTheme.typography.footnoteRegular,
                        tint = IpbTheme.colors.textPrimary.asBrush(),
                    )
                }
            }
            if (state.counter.isEmpty()) {
                IconButton(onClick = {
                    useComponent.handle(StoreCardEvent.AddToCart(state.id))
                }) {
                    BrushedIcon(
                        resId = R.drawable.ic_cart_pro, tint = IpbTheme.colors.iconPrimary.asBrush()
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
                company = "Lenovo",
                price = SimplePrice(1000),
                loan = "(Рассрочка: 2 платежа по 150 ₽)"
            ), useComponent = UseStoreCard.Empty()
        )
        StoreCard(
            state = StoreCardState(
                name = "Ноутбук Lenovo IdeaPad 3 15ADA05",
                company = "Lenovo",
                price = SimplePrice(1000),
                loan = "(Рассрочка: 2 платежа по 150 ₽)",
                counter = CounterState("1", 5)
            ), useComponent = UseStoreCard.Empty()
        )
    }
}