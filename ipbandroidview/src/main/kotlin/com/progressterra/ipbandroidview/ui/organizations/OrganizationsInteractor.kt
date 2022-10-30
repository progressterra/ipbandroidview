package com.progressterra.ipbandroidview.ui.organizations

import com.progressterra.ipbandroidview.actions.OpenDetails
import com.progressterra.ipbandroidview.actions.Refresh

interface OrganizationsInteractor : Refresh, OpenDetails<Organization> {

    class Empty : OrganizationsInteractor {

        override fun refresh() = Unit

        override fun openDetails(key: Organization) = Unit
    }
}