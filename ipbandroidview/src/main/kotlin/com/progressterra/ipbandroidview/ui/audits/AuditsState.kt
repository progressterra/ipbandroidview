package com.progressterra.ipbandroidview.ui.audits

import androidx.compose.runtime.Immutable

@Immutable
data class AuditsState(
    val documents: List<Document> = emptyList()
)
