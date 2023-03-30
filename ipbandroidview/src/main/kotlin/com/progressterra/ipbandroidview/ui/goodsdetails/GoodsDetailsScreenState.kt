package com.progressterra.ipbandroidview.ui.goodsdetails

import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.entities.GoodsItem

data class GoodsDetailsScreenState(
    val id: String = "",
//    val sizeTable: String = "",
    val screenState: ScreenState = ScreenState.LOADING,
    val goodsItem: GoodsItem = GoodsItem()
)
