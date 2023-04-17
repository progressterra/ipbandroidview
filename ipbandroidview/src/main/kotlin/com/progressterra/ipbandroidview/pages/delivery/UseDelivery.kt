package com.progressterra.ipbandroidview.pages.delivery

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.widgets.deliverypicker.DeliveryPickerEvent
import com.progressterra.ipbandroidview.widgets.deliverypicker.UseDeliveryPicker

interface UseDelivery : UseTopBar, UseDeliveryPicker, UseStateBox {

    class Empty : UseDelivery {

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: StateBoxEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit

        override fun handle(event: DeliveryPickerEvent) = Unit
    }
}