package com.progressterra.ipbandroidview.ui.documents

import com.progressterra.ipbandroidview.model.AuditDocument

sealed class DocumentsEffect {

    class UpdateCounter(val counter: Int) : DocumentsEffect()

    class OpenChecklist(val auditDocument: AuditDocument) : DocumentsEffect()

    object OpenOrganizations : DocumentsEffect()
}
