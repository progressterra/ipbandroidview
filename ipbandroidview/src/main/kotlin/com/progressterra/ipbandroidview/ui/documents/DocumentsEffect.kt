package com.progressterra.ipbandroidview.ui.documents

import com.progressterra.ipbandroidview.entities.AuditDocument
import com.progressterra.ipbandroidview.entities.ChecklistStatus
import com.progressterra.ipbandroidview.entities.Document

sealed class DocumentsEffect {

    class Archive(val title: String, val archived: List<Document>) : DocumentsEffect()

    class UpdateCounter(val counter: Int) : DocumentsEffect()

    class OpenChecklist(val auditDocument: AuditDocument, val initialStatus: ChecklistStatus) :
        DocumentsEffect()
}