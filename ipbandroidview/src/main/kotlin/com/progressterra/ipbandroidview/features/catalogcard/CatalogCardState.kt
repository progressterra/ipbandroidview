package com.progressterra.ipbandroidview.features.catalogcard

import com.progressterra.ipbandroidview.entities.IsEmpty


data class CatalogCardState(
    val id: String = "",
    val name: String = "",
    val image: String = "",
    val children: List<CatalogCardState> = emptyList()
) : IsEmpty {

    override fun isEmpty(): Boolean = this == CatalogCardState()
}
