package com.progressterra.ipbandroidview.widgets.orderitems

import com.progressterra.ipbandroidview.features.ordercard.OrderCardEvent
import com.progressterra.ipbandroidview.features.ordercard.UseOrderCard

interface UseOrderItems : UseOrderCard {

    class Empty : UseOrderItems {

        override fun handle(event: OrderCardEvent) = Unit
    }
}