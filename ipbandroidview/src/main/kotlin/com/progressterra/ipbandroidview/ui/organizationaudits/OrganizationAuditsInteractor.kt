package com.progressterra.ipbandroidview.ui.organizationaudits

interface OrganizationAuditsInteractor {

    fun onMapClick()

    fun onBack()

    class Empty : OrganizationAuditsInteractor {

        override fun onMapClick() = Unit

        override fun onBack() = Unit
    }
}