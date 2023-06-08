package com.progressterra.ipbandroidview.features.dialogpicker

import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton

interface UseDialogPicker : UseButton {

    fun handle(event: DialogPickerEvent)

    class Empty : UseDialogPicker {

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: DialogPickerEvent) = Unit
    }
}