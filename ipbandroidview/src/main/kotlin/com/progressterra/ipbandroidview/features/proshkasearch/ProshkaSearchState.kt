package com.progressterra.ipbandroidview.features.proshkasearch

import androidx.compose.runtime.Immutable

@Immutable
data class ProshkaSearchState(
    val text: String = "",
    val enabled: Boolean = true
)