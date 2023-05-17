package com.progressterra.ipbandroidview.features.avatarpicker

interface UseAvatarPicker {

    fun handle(event: AvatarPickerEvent)

    class Empty : UseAvatarPicker {

        override fun handle(event: AvatarPickerEvent) = Unit
    }
}