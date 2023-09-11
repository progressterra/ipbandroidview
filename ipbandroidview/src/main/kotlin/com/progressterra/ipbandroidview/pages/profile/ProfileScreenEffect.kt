package com.progressterra.ipbandroidview.pages.profile

sealed class ProfileScreenEffect {

    data object Logout : ProfileScreenEffect()

    data object WantThis : ProfileScreenEffect()

    data object Orders : ProfileScreenEffect()

    data object Favorites : ProfileScreenEffect()

    data object Support : ProfileScreenEffect()

    data object BankCards : ProfileScreenEffect()

    data object Auth : ProfileScreenEffect()

    data object Details : ProfileScreenEffect()

    data object Documents : ProfileScreenEffect()
}