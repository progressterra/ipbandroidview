package com.progressterra.ipbandroidview.ui.documents

import com.progressterra.ipbandroidview.model.AuditDocument
import com.progressterra.ipbandroidview.model.ChecklistStatus
import com.progressterra.ipbandroidview.model.Document

sealed class DocumentsEffect {

    class Archive(val archived: List<Document>) : DocumentsEffect()

    class UpdateCounter(val counter: Int) : DocumentsEffect()

    class OpenChecklist(val auditDocument: AuditDocument, val initialStatus: ChecklistStatus) :
        DocumentsEffect()
}