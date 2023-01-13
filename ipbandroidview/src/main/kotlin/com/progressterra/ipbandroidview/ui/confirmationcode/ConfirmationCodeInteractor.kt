package com.progressterra.ipbandroidview.ui.confirmationcode

interface ConfirmationCodeInteractor {

    fun resend()

    fun onNext()

    fun editCode(code: String)

    fun onBack()

    class Empty : ConfirmationCodeInteractor {

        override fun resend() = Unit

        override fun onNext() = Unit

        override fun editCode(code: String) = Unit

        override fun onBack() = Unit
    }
}