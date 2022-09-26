package com.progressterra.ipbandroidview.signup

sealed class SignUpEffect {

    object Back : SignUpEffect()

    object Skip : SignUpEffect()

    object Next : SignUpEffect()
}