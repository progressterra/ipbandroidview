package com.progressterra.ipbandroidview.ui.archive

import com.progressterra.ipbandroidview.entities.AuditDocument
import com.progressterra.ipbandroidview.entities.ChecklistStatus

sealed class ArchiveEffect {

    object Back : ArchiveEffect()

    class OpenChecklist(val auditDocument: AuditDocument, val initialStatus: ChecklistStatus) :
        ArchiveEffect()
}
