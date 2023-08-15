package com.progressterra.ipbandroidview.features.dialogpicker

import com.progressterra.ipbandroidview.entities.Citizenship

sealed class DialogPickerEvent {

    data object Close : DialogPickerEvent()

    class Select(val item: Citizenship) : DialogPickerEvent()
}