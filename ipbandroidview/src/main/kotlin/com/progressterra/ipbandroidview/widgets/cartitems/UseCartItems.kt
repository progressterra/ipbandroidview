package com.progressterra.ipbandroidview.widgets.cartitems

import com.progressterra.ipbandroidview.features.cartcard.CartCardEvent
import com.progressterra.ipbandroidview.features.cartcard.UseCartCard
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent

interface UseCartItems : UseCartCard {

    class Empty : UseCartItems {

        override fun handle(event: CounterEvent) = Unit

        override fun handle(event: CartCardEvent) = Unit
    }
}