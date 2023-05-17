package com.progressterra.ipbandroidview.features.interestspicker

sealed class InterestsPickerEvent(val id: String) {

    class Select(id: String) : InterestsPickerEvent(id)
}