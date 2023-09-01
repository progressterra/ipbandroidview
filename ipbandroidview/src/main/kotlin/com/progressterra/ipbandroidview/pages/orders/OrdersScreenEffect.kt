package com.progressterra.ipbandroidview.pages.orders

sealed class OrdersScreenEffect {

    data object Back : OrdersScreenEffect()

    class OpenDetails(val id: String) : OrdersScreenEffect()
}