package com.progressterra.ipbandroidview.ui.profile

sealed class ProfileEffect {

    object OpenDetails : ProfileEffect()

    object Orders : ProfileEffect()

    object Favorites : ProfileEffect()

    object Support : ProfileEffect()

    object Referral : ProfileEffect()
}
