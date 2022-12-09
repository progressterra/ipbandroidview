package com.progressterra.ipbandroidview.ui.documents

import com.progressterra.ipbandroidview.model.checklist.AuditDocument
import com.progressterra.ipbandroidview.model.checklist.ChecklistStatus
import com.progressterra.ipbandroidview.model.checklist.Document

sealed class DocumentsEffect {

    class Archive(val archived: List<Document>) : DocumentsEffect()

    class UpdateCounter(val counter: Int) : DocumentsEffect()

    class OpenChecklist(val auditDocument: AuditDocument, val initialStatus: ChecklistStatus) :
        DocumentsEffect()
}