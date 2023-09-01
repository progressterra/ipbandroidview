package com.progressterra.ipbandroidview.pages.signin

import androidx.annotation.StringRes

sealed class SignInScreenEffect {

    class Next(val data: String) : SignInScreenEffect()

    data object Skip : SignInScreenEffect()

    class Toast(@StringRes val data: Int) : SignInScreenEffect()
}
