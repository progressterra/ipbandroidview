package com.progressterra.ipbandroidview.pages.signin

import androidx.annotation.StringRes

sealed class SignInEffect {

    class Next(val phoneNumber: String) : SignInEffect()

    data object Skip : SignInEffect()

    class Toast(@StringRes val message: Int) : SignInEffect()
}
