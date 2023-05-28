package com.progressterra.ipbandroidview.features.avatar

sealed class AvatarEvent(val id: String) {

    class Click(id: String) : AvatarEvent(id)
}
