package com.progressterra.ipbandroidview.ui.signin

import androidx.annotation.StringRes

sealed class SignInEffect {

    object Next : SignInEffect()

    object Skip : SignInEffect()

    class Toast(@StringRes val message: Int) : SignInEffect()
}
