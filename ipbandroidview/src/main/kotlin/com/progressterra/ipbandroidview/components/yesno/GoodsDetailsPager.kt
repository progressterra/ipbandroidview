package com.progressterra.ipbandroidview.components.yesno

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.progressterra.ipbandroidview.dto.GoodsDetails
import com.progressterra.ipbandroidview.theme.AppTheme

@OptIn(ExperimentalPagerApi::class)
@Composable
fun GoodsDetailsPager(modifier: Modifier = Modifier, goodsDetails: List<GoodsDetails>) {
    HorizontalPager(count = goodsDetails.size) {
        when (goodsDetails[it]) {
            is GoodsDetails.Delivery -> TODO()
            is GoodsDetails.Description -> TODO()
            is GoodsDetails.Parameters -> TODO()
        }
    }
}

@Preview
@Composable
fun GoodsDetailsPagerPreview() {
    AppTheme {
        GoodsDetailsPager(
            goodsDetails = listOf(
                GoodsDetails.Description(name = "Some name", description = "Some description"),
                GoodsDetails.Parameters(listOf(GoodsDetails.Parameters.Item(name = "", value = "")))
            )
        )
    }
}