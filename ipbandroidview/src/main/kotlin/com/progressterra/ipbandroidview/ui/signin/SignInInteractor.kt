package com.progressterra.ipbandroidview.ui.signin

interface SignInInteractor {

    fun onNext()

    fun onSkip()

    fun onPhoneNumber(phoneNumber: String)

    class Empty : SignInInteractor {

        override fun onNext() = Unit

        override fun onSkip() = Unit

        override fun onPhoneNumber(phoneNumber: String) = Unit
    }
}