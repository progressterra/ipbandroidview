package com.progressterra.ipbandroidview.pages.overview

import com.progressterra.ipbandroidview.entities.ChecklistDocument

interface OverviewScreenNavigation {

    fun onArchive(title: String, docs: List<ChecklistDocument>)
}