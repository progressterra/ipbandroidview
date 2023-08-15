package com.progressterra.ipbandroidview.pages.pfppicker

sealed class PfpPickerScreenEvent {

    data object Next : PfpPickerScreenEvent()

    data object Back : PfpPickerScreenEvent()

    data object Skip : PfpPickerScreenEvent()
}