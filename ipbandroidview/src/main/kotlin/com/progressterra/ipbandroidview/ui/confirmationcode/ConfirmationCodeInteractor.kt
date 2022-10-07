package com.progressterra.ipbandroidview.ui.confirmationcode

interface ConfirmationCodeInteractor {

    fun onResend()

    fun onNext()

    fun onCode(code: String)

    class Empty : ConfirmationCodeInteractor {

        override fun onResend() = Unit

        override fun onNext() = Unit

        override fun onCode(code: String) = Unit
    }
}