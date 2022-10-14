package com.progressterra.ipbandroidview.ui.organizationaudits

interface OrganizationAuditsInteractor {

    fun onMapClick()

    fun onBack()

    fun onRefresh()

    fun onAuditClick(organizationAudit: OrganizationAudit)

    class Empty : OrganizationAuditsInteractor {

        override fun onMapClick() = Unit

        override fun onBack() = Unit

        override fun onRefresh() = Unit

        override fun onAuditClick(organizationAudit: OrganizationAudit) = Unit
    }
}