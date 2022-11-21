package com.progressterra.ipbandroidview.ui.goodsdetails

import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.GoodsDetails

data class GoodsDetailsScreenState(
    val id: String = "",
    val screenState: ScreenState = ScreenState.LOADING,
    val goodsDetails: GoodsDetails = GoodsDetails()
)
