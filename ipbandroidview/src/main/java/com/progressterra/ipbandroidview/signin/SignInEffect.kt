package com.progressterra.ipbandroidview.signin

import androidx.annotation.StringRes

sealed class SignInEffect {

    object Next : SignInEffect()

    object Skip : SignInEffect()

    object Back : SignInEffect()

    class Toast(@StringRes val message: Int) : SignInEffect()
}
