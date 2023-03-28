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
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.OrderGoods
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Immutable
interface GoodsLineState {

    val goods: List<OrderGoods>
}

@Composable
fun GoodsLine(
    modifier: Modifier = Modifier,
    state: GoodsLineState,
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
        Text(
            text = stringResource(R.string.goods),
            color = IpbTheme.colors.black,
            style = IpbTheme.typography.title
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(state.goods) {
                GoodsLineItem(state = it, openGoodsDetails = openGoodsDetails)
            }
        }
    }
}

private class GoodsLineStatePreview(override val goods: List<OrderGoods> = emptyList()) :
    GoodsLineState

@Preview
@Composable
private fun GoodsLinePreview() {
    IpbTheme {
        GoodsLine(state = GoodsLineStatePreview(), openGoodsDetails = {})
    }
}