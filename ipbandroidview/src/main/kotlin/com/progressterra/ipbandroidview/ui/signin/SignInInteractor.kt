package com.progressterra.ipbandroidview.ui.signin

interface SignInInteractor {

    fun onNext()

    fun onSkip()

    fun editPhoneNumber(phoneNumber: String)

    fun openUrl(url: String)

    class Empty : SignInInteractor {

        override fun onNext() = Unit

        override fun onSkip() = Unit

        override fun editPhoneNumber(phoneNumber: String) = Unit

        override fun openUrl(url: String) = Unit
    }
}