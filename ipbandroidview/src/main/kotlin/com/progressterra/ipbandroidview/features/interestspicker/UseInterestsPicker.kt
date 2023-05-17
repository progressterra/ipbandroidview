package com.progressterra.ipbandroidview.features.interestspicker

interface UseInterestsPicker {

    fun handle(event: InterestsPickerEvent)

    class Empty : UseInterestsPicker {

        override fun handle(event: InterestsPickerEvent) = Unit
    }
}