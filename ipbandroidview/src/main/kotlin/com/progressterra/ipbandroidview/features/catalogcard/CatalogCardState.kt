package com.progressterra.ipbandroidview.features.catalogcard

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.IsEmpty

@Immutable
data class CatalogCardState(
    val id: String = "",
    val name: String = "",
    val image: String = "",
    val children: List<CatalogCardState> = emptyList()
) : IsEmpty {

    override fun isEmpty(): Boolean = this == CatalogCardState()
}
