package com.progressterra.ipbandroidview.pages.orderlist

import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsState

sealed class OrdersListEvent {

    object Back : OrdersListEvent()

    class OpenDetails(val state: OrderDetailsState) : OrdersListEvent()
}