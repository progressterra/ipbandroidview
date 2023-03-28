package com.progressterra.ipbandroidview.ui.organizationaudits

import com.progressterra.ipbandroidview.entities.AuditDocument
import com.progressterra.ipbandroidview.entities.ChecklistStatus

sealed class OrganizationAuditsEffect {

    object Back : OrganizationAuditsEffect()

    class OpenChecklist(val auditDocument: AuditDocument, val initialStatus: ChecklistStatus) :
        OrganizationAuditsEffect()
}