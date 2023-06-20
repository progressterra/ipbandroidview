package com.progressterra.ipbandroidview.features.ordercard

interface UseOrderCard {

    fun handle(event: OrderCardEvent)

    class Empty : UseOrderCard {

        override fun handle(event: OrderCardEvent) = Unit
    }
}