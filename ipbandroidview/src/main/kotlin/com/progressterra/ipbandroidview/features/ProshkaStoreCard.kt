package com.progressterra.ipbandroidview.features

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import com.progressterra.ipbandroidview.model.SimplePrice
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.theme.Preview
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.Counter
import com.progressterra.ipbandroidview.shared.ui.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.CounterState
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.UseCounter

@Immutable
data class ProshkaStoreCardState(
    val id: String = "",
    val name: String = "",
    val company: String = "",
    val price: SimplePrice = SimplePrice(),
    val imageUrl: String = "",
    val loan: String = "",
    val counter: CounterState = CounterState()
)

sealed class ProshkaStoreCardEvent {

    object Open : ProshkaStoreCardEvent()

    object AddToCart : ProshkaStoreCardEvent()
}

interface UseProshkaStoreCard : UseCounter {

    fun handleEvent(id: String, event: ProshkaStoreCardEvent)

    class Empty : UseProshkaStoreCard {

        override fun handleEvent(id: String, event: CounterEvent) = Unit

        override fun handleEvent(id: String, event: ProshkaStoreCardEvent) = Unit
    }
}

@Composable
fun ProshkaStoreCard(
    modifier: Modifier = Modifier,
    state: ProshkaStoreCardState,
    useComponent: UseProshkaStoreCard
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .niceClickable {
                useComponent.handleEvent(
                    state.id, ProshkaStoreCardEvent.Open
                )
            }, verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        SimpleImage(
            modifier = Modifier
                .size(width = 157.dp, height = 122.dp)
                .clip(RoundedCornerShape(8.dp)),
            url = state.imageUrl,
            backgroundColor = IpbTheme.colors.background.asColor()
        )
        BrushedText(
            text = state.name,
            style = IpbTheme.typography.tertiaryText,
            tint = IpbTheme.colors.textPrimary1.asBrush(),
        )
        BrushedText(
            text = state.company,
            style = IpbTheme.typography.tertiaryText,
            tint = IpbTheme.colors.textTertiary1.asBrush(),
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                BrushedText(
                    text = state.price.toString(),
                    style = IpbTheme.typography.secondaryText,
                    tint = IpbTheme.colors.textPrimary2.asBrush(),
                )
                BrushedText(
                    text = state.loan,
                    style = IpbTheme.typography.tertiaryText,
                    tint = IpbTheme.colors.textPrimary1.asBrush(),
                )
            }
            if (state.counter.isEmpty()) IconButton(onClick = {
                useComponent.handleEvent(
                    state.id, ProshkaStoreCardEvent.AddToCart
                )
            }) {
                BrushedIcon(
                    resId = R.drawable.ic_cart_pro,
                    tint = IpbTheme.colors.iconPrimary1.asBrush()
                )
            }
            else Counter(
                state = state.counter,
                useComponent = useComponent
            )
        }
    }
}

@Preview
@Composable
private fun ProshkaStoreCardPreview() {
    Preview {
        ProshkaStoreCard(
            state = ProshkaStoreCardState(
                name = "Ноутбук Lenovo IdeaPad 3 15ADA05",
                company = "Lenovo",
                price = SimplePrice(1000),
                loan = "(Рассрочка: 2 платежа по 150 ₽)"
            ), useComponent = UseProshkaStoreCard.Empty()
        )
    }
}