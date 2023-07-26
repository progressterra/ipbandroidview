package com.progressterra.ipbandroidview.features.catalogcard

import androidx.compose.runtime.Immutable
import arrow.optics.optics

@Immutable
@optics data class CatalogCardState(
    val id: String = "",
    val name: String = "",
    val image: String = "",
    val children: List<CatalogCardState> = emptyList()
) { companion object }