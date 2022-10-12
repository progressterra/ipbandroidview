package com.progressterra.ipbandroidview.ui.organizationaudits

interface OrganizationAuditsInteractor {

    fun onMapClick()

    fun onBack()

    fun onRefresh()

    class Empty : OrganizationAuditsInteractor {

        override fun onMapClick() = Unit

        override fun onBack() = Unit

        override fun onRefresh() = Unit
    }
}