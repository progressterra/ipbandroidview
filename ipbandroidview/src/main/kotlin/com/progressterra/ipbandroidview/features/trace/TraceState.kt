package com.progressterra.ipbandroidview.features.trace

import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState


data class TraceState(
    val trace: List<CatalogCardState> = emptyList(),
    val current: CatalogCardState = CatalogCardState()
)