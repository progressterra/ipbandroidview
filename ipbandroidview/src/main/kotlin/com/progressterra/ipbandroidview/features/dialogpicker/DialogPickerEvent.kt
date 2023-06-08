package com.progressterra.ipbandroidview.features.dialogpicker

sealed class DialogPickerEvent {

    object Close : DialogPickerEvent()

    class Select(val item: DialogPickerState.Item) : DialogPickerEvent()
}