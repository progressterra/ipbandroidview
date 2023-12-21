package com.progressterra.ipbandroidview.pages.overview

import com.progressterra.ipbandroidview.entities.ChecklistDocument

sealed class OverviewEvent {

    data class Archived(val documents: List<ChecklistDocument>) : OverviewEvent()

    data class Complete(val documents: List<ChecklistDocument>) : OverviewEvent()

    data class Ongoing(val documents: List<ChecklistDocument>) : OverviewEvent()
}