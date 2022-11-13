package com.progressterra.ipbandroidview.ui.goodsdetails

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.GoodsDetails
import com.progressterra.ipbandroidview.model.component.Id

@Immutable
data class GoodsDetailsScreenState(
    override val id: String = "",
    val screenState: ScreenState = ScreenState.LOADING,
    val goodsDetails: GoodsDetails = GoodsDetails.Base()
) : Id, GoodsDetails by goodsDetails
