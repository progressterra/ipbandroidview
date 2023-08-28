package com.progressterra.ipbandroidview.pages.orderstatus

sealed class OrderStatusEvent {

    data object OnMain : OrderStatusEvent()

    data object OnBack : OrderStatusEvent()

    data class OnOrder(val id: String) : OrderStatusEvent()
}