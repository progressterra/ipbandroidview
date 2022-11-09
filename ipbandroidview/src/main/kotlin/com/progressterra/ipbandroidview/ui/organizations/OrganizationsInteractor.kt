package com.progressterra.ipbandroidview.ui.organizations

import com.progressterra.ipbandroidview.actions.Refresh

interface OrganizationsInteractor : Refresh {

    fun organizationDetails(organization: Organization)

    class Empty : OrganizationsInteractor {

        override fun refresh() = Unit

        override fun organizationDetails(organization: Organization) = Unit
    }
}