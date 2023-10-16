package com.progressterra.ipbandroidview.pages.avatarpicker

sealed class AvatarPickerScreenEffect {

    data object OnBack : AvatarPickerScreenEffect()

    data object OnNext : AvatarPickerScreenEffect()
}