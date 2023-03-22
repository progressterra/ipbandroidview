package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.model.OrderDetails
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun OrdersGoodsLine(
    modifier: Modifier = Modifier,
    state: OrderDetails,
    openGoodsDetails: (String) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(IpbTheme.shapes.medium)
            .background(IpbTheme.colors.surfaces)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(
                text = state.status,
                color = IpbTheme.colors.gray1,
                style = IpbTheme.typography.title
            )
            Text(
                text = state.number,
                color = IpbTheme.colors.gray2,
                style = IpbTheme.typography.tertiary
            )
        }
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(state.goods) {
                GoodsLineItem(state = it, openGoodsDetails = openGoodsDetails)
            }
        }
    }
}

@Preview
@Composable
private fun OrdersGoodsLinePreview() {
    IpbTheme {
        OrdersGoodsLine(state = OrderDetails(), openGoodsDetails = {})
    }
}