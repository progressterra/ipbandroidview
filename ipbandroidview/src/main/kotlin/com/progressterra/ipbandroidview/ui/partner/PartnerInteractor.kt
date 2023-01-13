package com.progressterra.ipbandroidview.ui.partner

interface PartnerInteractor {

    fun onBack()

    class Empty : PartnerInteractor {

        override fun onBack() = Unit
    }
}