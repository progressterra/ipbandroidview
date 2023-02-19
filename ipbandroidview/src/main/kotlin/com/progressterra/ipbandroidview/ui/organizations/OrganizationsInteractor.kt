package com.progressterra.ipbandroidview.ui.organizations

import com.progressterra.ipbandroidview.model.Organization

interface OrganizationsInteractor {

    fun refresh()

    fun onOrganizationDetails(organization: Organization)

    class Empty : OrganizationsInteractor {

        override fun refresh() = Unit

        override fun onOrganizationDetails(organization: Organization) = Unit
    }
}