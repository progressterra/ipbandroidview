package com.progressterra.ipbandroidview.pages.cart

import com.progressterra.ipbandroidview.features.cartcard.CartCardEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox
import com.progressterra.ipbandroidview.widgets.cartitems.UseCartItems
import com.progressterra.ipbandroidview.widgets.cartsummary.UseCartSummary

interface UseCartScreen : UseCartItems, UseStateBox, UseTopBar, UseCartSummary {

    class Empty : UseCartScreen {

        override fun handle(event: CartCardEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: CounterEvent) = Unit

        override fun handle(event: StateBoxEvent) = Unit
    }
}