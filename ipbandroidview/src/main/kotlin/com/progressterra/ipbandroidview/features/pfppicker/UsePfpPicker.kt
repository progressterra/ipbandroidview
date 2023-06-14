package com.progressterra.ipbandroidview.features.pfppicker

interface UsePfpPicker {

    fun handle(event: PfpPickerEvent)

    class Empty : UsePfpPicker {

        override fun handle(event: PfpPickerEvent) = Unit
    }
}