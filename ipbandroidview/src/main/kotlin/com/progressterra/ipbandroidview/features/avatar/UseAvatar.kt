package com.progressterra.ipbandroidview.features.avatar

interface UseAvatar {

    fun handle(event: AvatarEvent)

    class Empty : UseAvatar {

        override fun handle(event: AvatarEvent) = Unit
    }
}