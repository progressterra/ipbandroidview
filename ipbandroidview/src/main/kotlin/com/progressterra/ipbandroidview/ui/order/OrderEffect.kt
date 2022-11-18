package com.progressterra.ipbandroidview.ui.order

sealed class OrderEffect {

    object Back : OrderEffect()

    object City : OrderEffect()

    object PickUp : OrderEffect()

    class GoodsDetails(val goodsId: String) : OrderEffect()
}
