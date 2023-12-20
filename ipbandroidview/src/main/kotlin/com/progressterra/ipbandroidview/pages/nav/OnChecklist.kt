package com.progressterra.ipbandroidview.pages.nav

import com.progressterra.ipbandroidview.entities.AuditDocument
import com.progressterra.ipbandroidview.entities.ChecklistStatus

interface OnChecklist {

    fun onChecklist(auditDocument: AuditDocument, initialStatus: ChecklistStatus)
}