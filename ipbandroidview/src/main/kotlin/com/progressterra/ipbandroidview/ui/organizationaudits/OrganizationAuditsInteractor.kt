package com.progressterra.ipbandroidview.ui.organizationaudits

interface OrganizationAuditsInteractor {

    fun onMapClick()

    class Empty : OrganizationAuditsInteractor {

        override fun onMapClick() = Unit
    }
}