package com.progressterra.ipbandroidview.features.orderdetails

import com.progressterra.ipbandroidview.features.ordercard.OrderCardEvent
import com.progressterra.ipbandroidview.widgets.orderitems.UseOrderItems

interface UseOrderDetails : UseOrderItems {

    fun handle(event: OrderDetailsEvent)

    class Empty : UseOrderDetails {

        override fun handle(event: OrderDetailsEvent) = Unit

        override fun handle(event: OrderCardEvent) = Unit
    }
}