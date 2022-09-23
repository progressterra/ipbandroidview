package com.progressterra.ipbandroidview.confirmationcode

interface ConfirmationCodeInteractor {

    fun onBack()

    fun onResend()

    fun onNext()

    fun onCode(code: String)

    class Empty : ConfirmationCodeInteractor {

        override fun onBack() = Unit

        override fun onResend() = Unit

        override fun onNext() = Unit

        override fun onCode(code: String) = Unit
    }
}