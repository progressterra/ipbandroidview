package com.progressterra.ipbandroidview.pages.delivery

import com.progressterra.ipbandroidview.features.addresssuggestions.AddressSuggestionsEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.widgets.deliverypicker.UseDeliveryPicker

interface UseDelivery : UseTopBar, UseDeliveryPicker, UseStateColumn {

    class Empty : UseDelivery {

        override fun handle(event: StateColumnEvent) = Unit

        override fun handle(event: AddressSuggestionsEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit
    }
}