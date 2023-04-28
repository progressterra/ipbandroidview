package com.progressterra.ipbandroidview.features.cartcard


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.progressterra.ipbandroidview.shared.theme.toColor
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.counter.Counter
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun CartCard(
    modifier: Modifier = Modifier, state: CartCardState, useComponent: UseCartCard
) {
    Row(
        modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
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
                    useComponent.handle(
                        CartCardEvent.Open(state.id)
                    )
                }, verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            BrushedText(
                text = state.name,
                style = IpbTheme.typography.footnoteRegular,
                tint = IpbTheme.colors.textPrimary.asBrush(),
            )
            BrushedText(
                text = state.company,
                style = IpbTheme.typography.footnoteRegular,
                tint = IpbTheme.colors.textTertiary.asBrush(),
            )
            state.properties.forEach {
                when (it) {
                    is CartCardState.Property.Color -> Row(verticalAlignment = Alignment.CenterVertically) {
                        BrushedText(
                            text = "${it.name}: ",
                            style = IpbTheme.typography.footnoteRegular,
                            tint = IpbTheme.colors.textSecondary.asBrush()
                        )
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(15.dp)
                                .background(it.value.toColor())
                                .border(
                                    width = 1.dp,
                                    brush = IpbTheme.colors.secondary.asBrush(),
                                    shape = CircleShape
                                )
                        )
                    }

                    is CartCardState.Property.Size -> BrushedText(
                        text = "${it.name}: ${it.value}",
                        style = IpbTheme.typography.footnoteRegular,
                        tint = IpbTheme.colors.textSecondary.asBrush()
                    )
                }
            }
            BrushedText(
                text = state.price.toString(),
                style = IpbTheme.typography.subHeadlineRegular,
                tint = IpbTheme.colors.textPrimary2.asBrush(),
            )
            BrushedText(
                text = state.loan,
                style = IpbTheme.typography.footnoteRegular,
                tint = IpbTheme.colors.textPrimary.asBrush(),
            )
            Counter(
                state = state.counter, useComponent = useComponent
            )
        }
        IconButton(
            modifier = Modifier.size(20.dp),
            onClick = { useComponent.handle(CartCardEvent.RemoveFromCart(state.id)) }
        ) {
            BrushedIcon(
                resId = R.drawable.ic_trash,
                tint = IpbTheme.colors.iconTertiary.asBrush()
            )
        }
    }
}

@Preview
@Composable
private fun CartCardPreview() {
    Preview {
        CartCard(
            state = CartCardState(
                name = "Ноутбук Lenovo IdeaPad 3 15ADA05",
                company = "Lenovo",
                price = SimplePrice(1000),
                loan = "(Рассрочка: 2 платежа по 150 ₽)"
            ), useComponent = UseCartCard.Empty()
        )
    }
}