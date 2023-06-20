package com.progressterra.ipbandroidview.features.ordercard

sealed class OrderCardEvent(val id: String) {

    class GoodsDetails(id: String) : OrderCardEvent(id)
}