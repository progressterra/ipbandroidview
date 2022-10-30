package com.progressterra.ipbandroidview.ui.signup

import androidx.annotation.StringRes

sealed class SignUpEffect {

    object Skip : SignUpEffect()

    object Next : SignUpEffect()

    @Suppress("unused")
    class Toast(@StringRes val message: Int) : SignUpEffect()
}