package com.progressterra.ipbandroidview.ui.organizationedit

import com.progressterra.ipbandroidview.entities.AuditDocument
import com.progressterra.ipbandroidview.entities.ChecklistStatus

sealed class OrganizationEditEffect {

    object Back : OrganizationEditEffect()

    class OpenChecklist(val auditDocument: AuditDocument, val initialStatus: ChecklistStatus) :
        OrganizationEditEffect()
}