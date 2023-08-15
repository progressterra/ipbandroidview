package com.progressterra.ipbandroidview.pages.profile

sealed class ProfileEvent {

    data object Logout : ProfileEvent()

    data object Orders : ProfileEvent()

    data object Favorites : ProfileEvent()

    data object Support : ProfileEvent()

    data object BankCards : ProfileEvent()

    data object Auth : ProfileEvent()

    data object Details : ProfileEvent()

    data object Documents : ProfileEvent()
}