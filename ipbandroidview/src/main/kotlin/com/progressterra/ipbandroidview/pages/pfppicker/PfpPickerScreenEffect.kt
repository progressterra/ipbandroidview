package com.progressterra.ipbandroidview.pages.pfppicker

sealed class PfpPickerScreenEffect {

    data object Next : PfpPickerScreenEffect()

    data object Back : PfpPickerScreenEffect()

    data object Skip : PfpPickerScreenEffect()
}