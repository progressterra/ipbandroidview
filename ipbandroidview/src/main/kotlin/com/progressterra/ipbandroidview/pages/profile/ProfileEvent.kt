package com.progressterra.ipbandroidview.pages.profile

sealed class ProfileEvent {

    object Logout : ProfileEvent()

    object Orders : ProfileEvent()

    object Favorites : ProfileEvent()

    object Support : ProfileEvent()

    object Auth : ProfileEvent()

    object Details : ProfileEvent()

    object Documents : ProfileEvent()
}