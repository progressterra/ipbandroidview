package com.progressterra.ipbandroidview.pages.pfppicker

sealed class PfpPickerScreenEvent {

    object Next : PfpPickerScreenEvent()

    object Back : PfpPickerScreenEvent()

    object Skip : PfpPickerScreenEvent()
}