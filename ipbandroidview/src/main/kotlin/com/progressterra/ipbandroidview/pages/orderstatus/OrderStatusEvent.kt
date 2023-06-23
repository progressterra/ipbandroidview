package com.progressterra.ipbandroidview.pages.orderstatus

sealed class OrderStatusEvent {

    object OnMain : OrderStatusEvent()

    object OnBack : OrderStatusEvent()
}