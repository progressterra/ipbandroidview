package com.progressterra.ipbandroidview.pages.orderstatus

sealed class OrderStatusEvent {

    object OnMain : OrderStatusEvent()

    object OnRecipe : OrderStatusEvent()

    object OnBack : OrderStatusEvent()
}