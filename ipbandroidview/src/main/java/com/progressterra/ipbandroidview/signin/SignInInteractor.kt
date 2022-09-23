package com.progressterra.ipbandroidview.signin

interface SignInInteractor {

    fun onNext()

    fun onSkip()

    fun onBack()

    fun onPhoneNumber(phoneNumber: String)

    class Empty : SignInInteractor {

        override fun onNext() = Unit

        override fun onSkip() = Unit

        override fun onBack() = Unit

        override fun onPhoneNumber(phoneNumber: String) = Unit
    }
}