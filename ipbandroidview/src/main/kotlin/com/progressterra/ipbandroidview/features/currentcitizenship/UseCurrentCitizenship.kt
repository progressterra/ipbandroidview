package com.progressterra.ipbandroidview.features.currentcitizenship

import com.progressterra.ipbandroidview.features.dialogpicker.DialogPickerEvent
import com.progressterra.ipbandroidview.features.dialogpicker.UseDialogPicker
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent

interface UseCurrentCitizenship : UseDialogPicker {

    fun handle(event: CurrentCitizenshipEvent)

    class Empty : UseCurrentCitizenship {

        override fun handle(event: DialogPickerEvent) = Unit

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: CurrentCitizenshipEvent) = Unit
    }
}