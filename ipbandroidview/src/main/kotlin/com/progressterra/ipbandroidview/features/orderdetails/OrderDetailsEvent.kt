package com.progressterra.ipbandroidview.features.orderdetails

sealed class OrderDetailsEvent {

    data object Tracking : OrderDetailsEvent()

    data object Chat : OrderDetailsEvent()
}