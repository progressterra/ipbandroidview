package com.progressterra.ipbandroidview.ui.agreement

interface UseAgreement {

    fun onBack()

    fun mailTo(email: String)

    class Empty : UseAgreement {

        override fun mailTo(email: String) = Unit

        override fun onBack() = Unit
    }
}