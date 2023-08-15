package com.progressterra.ipbandroidview.pages.orderlist

sealed class OrdersListEvent {

    data object Back : OrdersListEvent()

    class OpenDetails(val id: String) : OrdersListEvent()
}