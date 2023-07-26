package com.progressterra.ipbandroidview.features.trace

import androidx.compose.runtime.Immutable
import arrow.optics.optics
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState

@Immutable
@optics
data class TraceState(
    val trace: List<CatalogCardState> = emptyList()
) { companion object }