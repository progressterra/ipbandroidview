package com.progressterra.ipbandroidview.features


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.model.SimplePrice
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.theme.Preview
import com.progressterra.ipbandroidview.shared.theme.toColor
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.Counter
import com.progressterra.ipbandroidview.shared.ui.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.CounterState
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.UseCounter

@Immutable
data class ProshkaCartCardState(
    val id: String = "",
    val name: String = "",
    val company: String = "",
    val price: SimplePrice = SimplePrice(),
    val imageUrl: String = "",
    val loan: String = "",
    val counter: CounterState = CounterState(),
    val properties: List<Property> = emptyList()
) {

    sealed class Property(val name: String, val value: String) {

        class Color(name: String, value: String) : Property(name, value)

        class Size(name: String, value: String) : Property(name, value)
    }
}

sealed class ProshkaCartCardEvent {

    object Open : ProshkaCartCardEvent()

    object RemoveFromCart : ProshkaCartCardEvent()
}

interface UseProshkaCartCard : UseCounter {

    fun handleEvent(id: String, event: ProshkaCartCardEvent)

    class Empty : UseProshkaCartCard {

        override fun handleEvent(id: String, event: CounterEvent) = Unit

        override fun handleEvent(id: String, event: ProshkaCartCardEvent) = Unit
    }
}

@Composable
fun ProshkaCartCard(
    modifier: Modifier = Modifier, state: ProshkaCartCardState, useComponent: UseProshkaCartCard
) {
    Row(
        modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SimpleImage(
            modifier = Modifier
                .size(width = 157.dp, height = 122.dp)
                .clip(RoundedCornerShape(8.dp)),
            url = state.imageUrl,
            backgroundColor = IpbTheme.colors.background.asColor()
        )
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .niceClickable {
                    useComponent.handleEvent(
                        state.id, ProshkaCartCardEvent.Open
                    )
                }, verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
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
            state.properties.forEach {
                when (it) {
                    is ProshkaCartCardState.Property.Color -> Row(verticalAlignment = Alignment.CenterVertically) {
                        BrushedText(
                            text = "${it.name}: ",
                            style = IpbTheme.typography.tertiaryText,
                            tint = IpbTheme.colors.textSecondary.asBrush()
                        )
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(15.dp)
                                .background(it.value.toColor())
                                .border(
                                    width = 1.dp,
                                    brush = IpbTheme.colors.secondary1.asBrush(),
                                    shape = CircleShape
                                )
                        )
                    }
                    is ProshkaCartCardState.Property.Size -> BrushedText(
                        text = "${it.name}: ${it.value}",
                        style = IpbTheme.typography.tertiaryText,
                        tint = IpbTheme.colors.textSecondary.asBrush()
                    )
                }
            }
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
            Counter(
                state = state.counter, useComponent = useComponent
            )
        }
        IconButton(
            onClick = { useComponent.handleEvent(state.id, ProshkaCartCardEvent.RemoveFromCart) }
        ) {
            BrushedIcon(
                resId = R.drawable.ic_trash_pro,
                tint = IpbTheme.colors.iconTertiary1.asBrush()
            )
        }
    }
}

@Preview
@Composable
private fun ProshkaCartCardPreview() {
    Preview {
        ProshkaCartCard(
            state = ProshkaCartCardState(
                name = "Ноутбук Lenovo IdeaPad 3 15ADA05",
                company = "Lenovo",
                price = SimplePrice(1000),
                loan = "(Рассрочка: 2 платежа по 150 ₽)"
            ), useComponent = UseProshkaCartCard.Empty()
        )
    }
}