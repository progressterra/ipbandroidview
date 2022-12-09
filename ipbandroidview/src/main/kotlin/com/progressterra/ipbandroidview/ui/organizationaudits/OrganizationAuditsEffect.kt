package com.progressterra.ipbandroidview.ui.organizationaudits

import com.progressterra.ipbandroidview.model.checklist.AuditDocument
import com.progressterra.ipbandroidview.model.checklist.ChecklistStatus

sealed class OrganizationAuditsEffect {

    object Back : OrganizationAuditsEffect()

    class OpenChecklist(val auditDocument: AuditDocument, val initialStatus: ChecklistStatus) :
        OrganizationAuditsEffect()
}