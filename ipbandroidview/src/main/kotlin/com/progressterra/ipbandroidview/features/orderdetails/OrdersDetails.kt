package com.progressterra.ipbandroidview.features.orderdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun OrdersDetails(
    modifier: Modifier = Modifier, state: OrderDetails, useComponent: UseOrderDetails
) {

    @Composable
    fun GoodsLineItem(
        modifier: Modifier = Modifier,
        state: OrderDetails.OrderGoods,
        openGoodsDetails: (String) -> Unit
    ) {
        Box(modifier = modifier) {
            SimpleImage(
                modifier = Modifier
                    .size(119.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .niceClickable { openGoodsDetails(state.id) },
                url = state.image,
                backgroundColor = IpbTheme.colors.surface.asColor()
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(6.dp))
                    .background(IpbTheme.colors.background.asBrush())
                    .padding(4.dp)
            ) {
                BrushedText(
                    text = "x${state.inCartCounter}",
                    tint = IpbTheme.colors.textPrimary3.asBrush(),
                    style = IpbTheme.typography.caption
                )
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            BrushedText(
                text = state.status,
                tint = IpbTheme.colors.textPrimary.asBrush(),
                style = IpbTheme.typography.title
            )
            BrushedText(
                text = state.number,
                tint = IpbTheme.colors.textTertiary.asBrush(),
                style = IpbTheme.typography.footnoteRegular
            )
        }
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(state.goods) {
                GoodsLineItem(
                    state = it,
                    openGoodsDetails = { id -> useComponent.handle(OrderDetailsEvent.GoodsDetails(id)) })
            }
        }
    }
}