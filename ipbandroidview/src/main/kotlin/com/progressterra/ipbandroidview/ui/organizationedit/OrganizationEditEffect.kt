package com.progressterra.ipbandroidview.ui.organizationedit

import com.progressterra.ipbandroidview.model.AuditDocument
import com.progressterra.ipbandroidview.model.ChecklistStatus

sealed class OrganizationEditEffect {

    object Back : OrganizationEditEffect()

    class OpenChecklist(val auditDocument: AuditDocument, val initialStatus: ChecklistStatus) :
        OrganizationEditEffect()
}