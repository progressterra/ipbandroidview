package com.progressterra.ipbandroidview.ui.organizations

interface OrganizationsInteractor {

    fun onOrganization(organization: Organization)

    fun onRefresh()

    class Empty : OrganizationsInteractor {

        override fun onRefresh() = Unit

        override fun onOrganization(organization: Organization) = Unit
    }
}