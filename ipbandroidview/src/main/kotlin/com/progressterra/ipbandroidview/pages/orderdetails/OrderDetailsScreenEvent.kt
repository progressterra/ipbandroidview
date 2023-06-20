package com.progressterra.ipbandroidview.pages.orderdetails

sealed class OrderDetailsScreenEvent {

    object Back : OrderDetailsScreenEvent()

    class OpenGoods(val goodsId: String) : OrderDetailsScreenEvent()
}