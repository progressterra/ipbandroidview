package com.progressterra.ipbandroidview.pages.orders

sealed class OrdersEvent {

    object Back : OrdersEvent()

    class GoodsDetails(val goodsId: String) : OrdersEvent()
}