package com.progressterra.ipbandroidview.ui.signin

import androidx.annotation.StringRes

sealed class SignInEffect {

    class OpenNext(val phoneNumber: String) : SignInEffect()

    object Skip : SignInEffect()

    @Suppress("unused")
    class ShowToast(@StringRes val message: Int) : SignInEffect()
}
