package com.progressterra.ipbandroidview.ui.organizations

interface OrganizationsInteractor {

    fun onOrganization(id: String)

    class Empty : OrganizationsInteractor {

        override fun onOrganization(id: String) = Unit
    }
}