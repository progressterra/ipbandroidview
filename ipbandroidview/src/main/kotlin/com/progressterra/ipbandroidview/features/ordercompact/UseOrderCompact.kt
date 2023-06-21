package com.progressterra.ipbandroidview.features.ordercompact

interface UseOrderCompact {

    fun handle(event: OrderCompactEvent)

    class Empty : UseOrderCompact {

        override fun handle(event: OrderCompactEvent) = Unit
    }
}