package com.progressterra.ipbandroidview.ui.orders

sealed class OrdersEffect {

    object Back : OrdersEffect()

    class GoodsDetails(val goodsId: String) : OrdersEffect()
}