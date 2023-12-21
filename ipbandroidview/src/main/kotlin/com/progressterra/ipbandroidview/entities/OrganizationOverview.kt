package com.progressterra.ipbandroidview.entities

import androidx.compose.runtime.Immutable

@Immutable
data class OrganizationOverview(
    val name: String,
    val ongoing: List<ChecklistDocument>,
    val completed: List<ChecklistDocument>,
    val archived: List<ChecklistDocument>
)