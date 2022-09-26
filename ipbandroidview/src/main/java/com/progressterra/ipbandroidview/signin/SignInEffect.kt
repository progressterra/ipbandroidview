package com.progressterra.ipbandroidview.signin

sealed class SignInEffect {

    object Next : SignInEffect()

    object Skip : SignInEffect()

    object Back : SignInEffect()
}
