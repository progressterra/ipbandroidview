package com.progressterra.ipbandroidview.pages.signup

sealed class SignUpScreenEffect {

    data object OnNext : SignUpScreenEffect()

    data object OnSkip : SignUpScreenEffect()

    class OpenPhoto(val data: String) : SignUpScreenEffect()
}
