package com.progressterra.ipbandroidview.pages.overview

import com.progressterra.ipbandroidview.entities.AuditDocument
import com.progressterra.ipbandroidview.entities.ChecklistStatus

sealed class OverviewEffect {

    data class OnChecklist(val data: Pair<AuditDocument, ChecklistStatus>) : OverviewEffect()

    data class UpdateOngoingCounter(val counter: Int) : OverviewEffect()
}
