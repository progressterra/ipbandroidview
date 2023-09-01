package com.progressterra.ipbandroidview.pages.orderstatus

sealed class OrderStatusScreenEffect {

    data object OnMain : OrderStatusScreenEffect()

    data object OnBack : OrderStatusScreenEffect()

    data class OnOrder(val data: String) : OrderStatusScreenEffect()
}