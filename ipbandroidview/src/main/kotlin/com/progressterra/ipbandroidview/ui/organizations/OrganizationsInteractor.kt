package com.progressterra.ipbandroidview.ui.organizations

interface OrganizationsInteractor {

    fun onOrganization(organization: Organization)

    class Empty : OrganizationsInteractor {

        override fun onOrganization(organization: Organization) = Unit
    }
}