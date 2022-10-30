package com.progressterra.ipbandroidview.ui.signup

import androidx.annotation.StringRes

sealed class SignUpEffect {

    object Skip : SignUpEffect()

    object OpenNext : SignUpEffect()

    @Suppress("unused")
    class ShowToast(@StringRes val message: Int) : SignUpEffect()
}