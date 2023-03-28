package com.progressterra.ipbandroidview.ui.organizations

import com.progressterra.ipbandroidview.composable.PartnerBlockEvent
import com.progressterra.ipbandroidview.entities.Organization

interface OrganizationsInteractor {

    fun refresh()

    fun onOrganizationDetails(organization: Organization)

    fun handle(event: PartnerBlockEvent)

    class Empty : OrganizationsInteractor {

        override fun refresh() = Unit

        override fun onOrganizationDetails(organization: Organization) = Unit

        override fun handle(event: PartnerBlockEvent) = Unit
    }
}