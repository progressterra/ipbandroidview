package com.progressterra.ipbandroidview.ui.profiledetails

import androidx.annotation.StringRes

sealed class ProfileDetailsEffect {

    object GoBack : ProfileDetailsEffect()

    object UpdateUserInfo : ProfileDetailsEffect()

    object Logout : ProfileDetailsEffect()

    @Suppress("unused")
    class Toast(@StringRes val message: Int) : ProfileDetailsEffect()
}
