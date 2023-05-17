package com.progressterra.ipbandroidview.features.avatarpicker

sealed class AvatarPickerEvent(val id: String) {

    class Select(id: String) : AvatarPickerEvent(id)
}
