package com.progressterra.ipbandroidview.ui.signin

import androidx.annotation.StringRes

sealed class SignInEffect {

    class Next(val phoneNumber: String) : SignInEffect()

    object Skip : SignInEffect()

    class Toast(@StringRes val message: Int) : SignInEffect()
}
