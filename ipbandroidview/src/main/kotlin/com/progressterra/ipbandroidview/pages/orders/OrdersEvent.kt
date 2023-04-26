package com.progressterra.ipbandroidview.pages.orders

sealed class OrdersEvent {

    class GoodsDetails(val goodsId: String) : OrdersEvent()

    object Back : OrdersEvent()
}