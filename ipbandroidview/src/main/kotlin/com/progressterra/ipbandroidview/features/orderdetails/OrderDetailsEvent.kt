package com.progressterra.ipbandroidview.features.orderdetails

sealed class OrderDetailsEvent {

    class GoodsDetails(val id: String): OrderDetailsEvent()
}