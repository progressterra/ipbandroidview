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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.model.OrderGoods
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun GoodsLine(
    modifier: Modifier = Modifier,
    state: () -> List<OrderGoods>,
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
        Text(
            text = stringResource(R.string.goods),
            color = AppTheme.colors.black,
            style = AppTheme.typography.title
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)) {
            items(state()) {
                GoodsLineItem(state = { it }, openGoodsDetails = openGoodsDetails)
            }
        }
    }
}

@Preview
@Composable
private fun GoodsLinePreview() {
    AppTheme {
        GoodsLine(state = {
            emptyList()
        }, openGoodsDetails = {})
    }
}