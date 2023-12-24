package com.progressterra.ipbandroidview.pages.overview

import com.progressterra.ipbandroidview.entities.AuditDocument
import com.progressterra.ipbandroidview.entities.ChecklistStatus

sealed class OverviewEffect {

    data class OnChecklist(val document: AuditDocument, val status: ChecklistStatus) :
        OverviewEffect()
}
