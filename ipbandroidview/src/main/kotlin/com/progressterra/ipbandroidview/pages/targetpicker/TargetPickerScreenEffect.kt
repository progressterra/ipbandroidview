package com.progressterra.ipbandroidview.pages.targetpicker

import com.progressterra.ipbandroidview.entities.DatingTarget

sealed class TargetPickerScreenEffect {

    data object OnBack : TargetPickerScreenEffect()

    data class OnNext(val target: DatingTarget) : TargetPickerScreenEffect()
}