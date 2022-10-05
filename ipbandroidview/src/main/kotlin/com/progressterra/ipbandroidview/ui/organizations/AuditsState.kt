package com.progressterra.ipbandroidview.ui.organizations

import com.google.errorprone.annotations.Immutable

@Immutable
data class AuditsState(
    val completedAudits: List<Audit> = emptyList(),
    val ongoingAudits: List<Audit> = emptyList()
)
