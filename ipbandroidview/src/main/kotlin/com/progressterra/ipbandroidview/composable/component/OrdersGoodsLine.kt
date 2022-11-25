package com.progressterra.ipbandroidview.composable.component

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
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun OrdersGoodsLine(
    modifier: Modifier = Modifier,
    state: () -> OrderDetails,
    openGoodsDetails: (String) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .padding(AppTheme.dimensions.medium),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiniest)) {
            Text(
                text = state().status,
                color = AppTheme.colors.gray1,
                style = AppTheme.typography.title
            )
            Text(
                text = state().number,
                color = AppTheme.colors.gray2,
                style = AppTheme.typography.tertiaryText
            )
        }
        LazyRow(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)) {
            items(state().goods) {
                GoodsLineItem(state = { it }, openGoodsDetails = openGoodsDetails)
            }
        }
    }
}

@Preview
@Composable
private fun OrdersGoodsLinePreview() {
    AppTheme {
        OrdersGoodsLine(state = {
            OrderDetails()
        }, openGoodsDetails = {})
    }
}