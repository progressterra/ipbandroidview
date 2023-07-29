package com.progressterra.ipbandroidview.features.trace

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState

@Immutable
data class TraceState(
    val trace: List<CatalogCardState> = emptyList()
)