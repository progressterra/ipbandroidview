package com.progressterra.ipbandroidview.ui.organizations

import com.progressterra.ipbandroidview.model.checklist.Organization

interface OrganizationsInteractor {

    fun refresh()

    fun onOrganizationDetails(organization: Organization)

    fun onPartner()

    class Empty : OrganizationsInteractor {

        override fun onPartner() = Unit

        override fun refresh() = Unit

        override fun onOrganizationDetails(organization: Organization) = Unit
    }
}