package com.progressterra.ipbandroidview.features.orderdetails

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidapi.api.cart.models.TypeStatusOrder
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Installment
import com.progressterra.ipbandroidview.entities.Price
import com.progressterra.ipbandroidview.entities.canBeTracker
import com.progressterra.ipbandroidview.entities.toString
import com.progressterra.ipbandroidview.features.ordercard.OrderCardState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.Icon
import com.progressterra.ipbandroidview.shared.ui.Text
import com.progressterra.ipbandroidview.widgets.orderitems.OrderItems
import com.progressterra.ipbandroidview.widgets.orderitems.OrderItemsState

@Composable
fun OrderDetails(
    modifier: Modifier = Modifier, state: OrderDetailsState, useComponent: UseOrderDetails
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${stringResource(R.string.order_from)} ${state.date}",
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                    style = IpbTheme.typography.title
                )
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                    if (state.status.canBeTracker()) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            IconButton(
                                modifier = Modifier.size(24.dp),
                                onClick = { useComponent.handle(OrderDetailsEvent.Tracking) }
                            ) {
                                Icon(
                                    resId = R.drawable.ic_map,
                                    tint = IpbTheme.colors.iconTertiary.asBrush()
                                )
                            }
                            Text(
                                text = stringResource(R.string.track),
                                tint = IpbTheme.colors.textTertiary.asBrush(),
                                style = IpbTheme.typography.footnoteRegular
                            )
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = { useComponent.handle(OrderDetailsEvent.Chat) }
                        ) {
                            Icon(
                                resId = R.drawable.ic_chat,
                                tint = IpbTheme.colors.iconTertiary.asBrush()
                            )
                        }
                        Text(
                            text = stringResource(R.string.order_chat),
                            tint = IpbTheme.colors.textTertiary.asBrush(),
                            style = IpbTheme.typography.footnoteRegular
                        )
                    }
                }
            }
            Text(
                text = state.number,
                tint = IpbTheme.colors.textTertiary.asBrush(),
                style = IpbTheme.typography.footnoteRegular
            )
            Text(
                text = state.status.toString { stringResource(id = it) },
                tint = IpbTheme.colors.textPrimary.asBrush(),
                style = IpbTheme.typography.subHeadlineBold
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${state.count} ${stringResource(R.string.sum_of_goods)} ",
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                    style = IpbTheme.typography.body
                )
                Text(
                    text = state.totalPrice.toString(),
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                    style = IpbTheme.typography.body
                )
            }
        }
        OrderItems(
            state = state.goods, useComponent = useComponent
        )
    }
}

@Composable
@Preview
private fun OrderDetailsPreview() {
    IpbTheme {
        OrderDetails(
            state = OrderDetailsState(
                id = "dicam", number = "alienum", status = TypeStatusOrder.DELIVERED,
                goods = OrderItemsState(
                    items = listOf(
                        OrderCardState(
                            id = "mnesarchum",
                            name = "Kris Sheppard",
                            price = Price(price = 4541),
                            image = "https://duckduckgo.com/?q=sodales",
                            installment = Installment(
                                months = 7486, perMonth = Price(price = 1309)
                            )
                        ), OrderCardState(
                            id = "mnesarchum",
                            name = "Kris Sheppard",
                            price = Price(price = 4541),
                            image = "https://duckduckgo.com/?q=sodales",
                            installment = Installment(
                                months = 7486, perMonth = Price(price = 1309)
                            )
                        )
                    )
                ),
                date = "13.10", count = 5, totalPrice = Price(price = 0),
            ), useComponent = UseOrderDetails.Empty()
        )
    }
}