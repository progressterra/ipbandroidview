package com.progressterra.ipbandroidview.ui.organizationaudits

interface OrganizationAuditsInteractor {

    fun mapClick()

    fun onBack()

    fun refresh()

    fun onAuditDetails(audit: OrganizationAudit)

    class Empty : OrganizationAuditsInteractor {

        override fun mapClick() = Unit

        override fun onBack() = Unit

        override fun refresh() = Unit

        override fun onAuditDetails(audit: OrganizationAudit) = Unit
    }
}