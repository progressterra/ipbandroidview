package com.progressterra.ipbandroidview.ui.organizationaudits

import com.progressterra.ipbandroidview.model.AuditDocument

sealed class OrganizationAuditsEffect {

    object Back : OrganizationAuditsEffect()

    @Suppress("unused")
    class OpenChecklist(
        val auditDocument: AuditDocument
    ) : OrganizationAuditsEffect()
}