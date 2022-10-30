package com.progressterra.ipbandroidview.ui.profiledetails

import androidx.annotation.StringRes

sealed class ProfileDetailsEffect {

    object Back : ProfileDetailsEffect()

    object UpdateUserInfo : ProfileDetailsEffect()

    object Logout : ProfileDetailsEffect()

    @Suppress("unused")
    class ShowToast(@StringRes val message: Int) : ProfileDetailsEffect()
}
