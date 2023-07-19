package com.progressterra.ipbandroidview.pages.orderlist

sealed class OrdersListEvent {

    object Back : OrdersListEvent()

    class OpenDetails(val id: String) : OrdersListEvent()
}