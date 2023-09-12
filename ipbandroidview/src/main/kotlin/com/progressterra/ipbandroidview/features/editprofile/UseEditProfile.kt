package com.progressterra.ipbandroidview.features.editprofile

interface UseEditProfile {

    fun handle(event: EditProfileEvent)

    class Empty : UseEditProfile {

        override fun handle(event: EditProfileEvent) = Unit
    }
}