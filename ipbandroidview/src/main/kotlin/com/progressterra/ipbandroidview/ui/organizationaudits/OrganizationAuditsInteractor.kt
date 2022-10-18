package com.progressterra.ipbandroidview.ui.organizationaudits

import com.progressterra.ipbandroidview.actions.Refresh

interface OrganizationAuditsInteractor: Refresh {

    fun onMapClick()

    fun onBack()

    fun onAuditClick(organizationAudit: OrganizationAudit)

    class Empty : OrganizationAuditsInteractor {

        override fun onMapClick() = Unit

        override fun onBack() = Unit

        override fun refresh() = Unit

        override fun onAuditClick(organizationAudit: OrganizationAudit) = Unit
    }
}