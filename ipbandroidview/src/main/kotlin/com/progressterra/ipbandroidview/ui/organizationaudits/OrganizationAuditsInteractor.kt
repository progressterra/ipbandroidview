package com.progressterra.ipbandroidview.ui.organizationaudits

import com.progressterra.ipbandroidview.actions.Back
import com.progressterra.ipbandroidview.actions.OpenDetails
import com.progressterra.ipbandroidview.actions.Refresh

interface OrganizationAuditsInteractor: Refresh, Back, OpenDetails<OrganizationAudit> {

    fun onMapClick()

    class Empty : OrganizationAuditsInteractor {

        override fun onMapClick() = Unit

        override fun back() = Unit

        override fun refresh() = Unit

        override fun openDetails(key: OrganizationAudit) = Unit
    }
}