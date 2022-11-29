package com.progressterra.ipbandroidview.ui.documents

import com.progressterra.ipbandroidview.model.AuditDocument
import com.progressterra.ipbandroidview.model.ChecklistStatus

sealed class DocumentsEffect {

    class UpdateCounter(val counter: Int) : DocumentsEffect()

    class OpenChecklist(val auditDocument: AuditDocument, val initialStatus: ChecklistStatus) :
        DocumentsEffect()

    object OpenOrganizations : DocumentsEffect()
}
