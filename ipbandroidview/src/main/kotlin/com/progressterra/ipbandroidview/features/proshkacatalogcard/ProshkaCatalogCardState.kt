package com.progressterra.ipbandroidview.features.proshkacatalogcard

import androidx.compose.runtime.Immutable

@Immutable
data class ProshkaCatalogCardState(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = ""
)