package com.progressterra.ipbandroidview.pages.organizationaudits

import com.progressterra.ipbandroidview.entities.AuditDocument
import com.progressterra.ipbandroidview.entities.ChecklistStatus

sealed class OrganizationAuditsEffect {

    data object OnBack : OrganizationAuditsEffect()

    data class OnChecklist(
        val auditDocument: AuditDocument,
        val initialStatus: ChecklistStatus
    ) : OrganizationAuditsEffect()
}