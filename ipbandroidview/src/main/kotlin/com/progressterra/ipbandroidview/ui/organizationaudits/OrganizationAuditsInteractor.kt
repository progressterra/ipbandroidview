package com.progressterra.ipbandroidview.ui.organizationaudits

import com.progressterra.ipbandroidview.actions.Back
import com.progressterra.ipbandroidview.actions.Refresh

interface OrganizationAuditsInteractor : Refresh, Back {

    fun auditDetails(audit: OrganizationAudit)

    fun onMapClick()

    class Empty : OrganizationAuditsInteractor {

        override fun onMapClick() = Unit

        override fun back() = Unit

        override fun refresh() = Unit

        override fun auditDetails(audit: OrganizationAudit) = Unit
    }
}