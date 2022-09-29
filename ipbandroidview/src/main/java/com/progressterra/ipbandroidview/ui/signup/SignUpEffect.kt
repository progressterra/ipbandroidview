package com.progressterra.ipbandroidview.ui.signup

import androidx.annotation.StringRes

sealed class SignUpEffect {

    object Back : SignUpEffect()

    object Skip : SignUpEffect()

    object Next : SignUpEffect()

    class Toast(@StringRes val message: Int) : SignUpEffect()
}