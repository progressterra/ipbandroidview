package com.progressterra.ipbandroidview.pages.goodsdetails

import androidx.annotation.StringRes

sealed class GoodsDetailsEvent {

    object Back : GoodsDetailsEvent()

    class OpenImage(val image: String) : GoodsDetailsEvent()

    class GoodsDetails(val id: String) : GoodsDetailsEvent()

    class Toast(@StringRes val message: Int) : GoodsDetailsEvent()
}
