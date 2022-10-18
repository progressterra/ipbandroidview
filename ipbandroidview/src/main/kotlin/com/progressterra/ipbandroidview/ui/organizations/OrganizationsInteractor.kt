package com.progressterra.ipbandroidview.ui.organizations

import com.progressterra.ipbandroidview.actions.Refresh

interface OrganizationsInteractor : Refresh {

    fun onOrganization(organization: Organization)

    class Empty : OrganizationsInteractor {

        override fun refresh() = Unit

        override fun onOrganization(organization: Organization) = Unit
    }
}