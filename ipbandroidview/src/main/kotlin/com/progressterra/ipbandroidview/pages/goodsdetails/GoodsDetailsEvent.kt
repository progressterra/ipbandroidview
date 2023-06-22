package com.progressterra.ipbandroidview.pages.goodsdetails

sealed class GoodsDetailsEvent {

    object Back : GoodsDetailsEvent()

    class OpenImage(val image: String) : GoodsDetailsEvent()

    class GoodsDetails(val id: String) : GoodsDetailsEvent()

    object Refresh : GoodsDetailsEvent()
}
