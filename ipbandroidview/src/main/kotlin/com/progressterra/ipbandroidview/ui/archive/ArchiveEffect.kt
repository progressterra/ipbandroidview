package com.progressterra.ipbandroidview.ui.archive

import com.progressterra.ipbandroidview.model.checklist.AuditDocument
import com.progressterra.ipbandroidview.model.checklist.ChecklistStatus

sealed class ArchiveEffect {

    object Back : ArchiveEffect()

    class OpenChecklist(val auditDocument: AuditDocument, val initialStatus: ChecklistStatus) :
        ArchiveEffect()
}
