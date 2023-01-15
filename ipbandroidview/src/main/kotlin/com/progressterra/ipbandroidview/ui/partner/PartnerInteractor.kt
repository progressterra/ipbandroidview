package com.progressterra.ipbandroidview.ui.partner

interface PartnerInteractor {

    fun onBack()

    fun openWebsite(url: String)

    fun openPhone(phone: String)

    class Empty : PartnerInteractor {

        override fun onBack() = Unit

        override fun openWebsite(url: String) = Unit

        override fun openPhone(phone: String) = Unit
    }
}