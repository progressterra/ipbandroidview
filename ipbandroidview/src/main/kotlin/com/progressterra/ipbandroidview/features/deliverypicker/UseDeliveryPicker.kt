package com.progressterra.ipbandroidview.features.deliverypicker

import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseDeliveryPicker : UseTextField, UseButton {

    fun handle(event: DeliveryPickerEvent)
}