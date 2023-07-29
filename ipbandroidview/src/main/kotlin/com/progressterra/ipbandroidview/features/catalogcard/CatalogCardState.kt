package com.progressterra.ipbandroidview.features.catalogcard

import androidx.compose.runtime.Immutable

@Immutable
data class CatalogCardState(
    val id: String = "",
    val name: String = "",
    val image: String = "",
    val children: List<CatalogCardState> = emptyList()
)