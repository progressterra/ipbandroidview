package com.progressterra.ipbandroidview.pages.overview

import com.progressterra.ipbandroidview.entities.ChecklistDocument

sealed class OverviewEvent {

    data class OnChecklist(val data: ChecklistDocument) : OverviewEvent()

    data class UpdateOngoingCounter(val counter: Int) : OverviewEvent()
}