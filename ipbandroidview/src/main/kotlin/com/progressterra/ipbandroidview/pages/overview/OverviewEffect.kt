package com.progressterra.ipbandroidview.pages.overview

import com.progressterra.ipbandroidview.entities.ChecklistDocument

sealed class OverviewEffect {

    class Archive(val title: String, val archived: List<ChecklistDocument>) : OverviewEffect()
}
