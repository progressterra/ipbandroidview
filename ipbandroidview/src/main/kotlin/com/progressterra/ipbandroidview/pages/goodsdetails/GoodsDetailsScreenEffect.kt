package com.progressterra.ipbandroidview.pages.goodsdetails

import androidx.annotation.StringRes

sealed class GoodsDetailsScreenEffect {

    data object Back : GoodsDetailsScreenEffect()

    data object Delivery : GoodsDetailsScreenEffect()

    data object OnAuth : GoodsDetailsScreenEffect()

    class OpenImage(val data: String) : GoodsDetailsScreenEffect()

    class GoodsDetails(val data: String) : GoodsDetailsScreenEffect()

    class Toast(@StringRes val data: Int) : GoodsDetailsScreenEffect()
}
