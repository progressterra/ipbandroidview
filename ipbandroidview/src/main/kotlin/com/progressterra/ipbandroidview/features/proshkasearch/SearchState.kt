package com.progressterra.ipbandroidview.features.proshkasearch

import androidx.compose.runtime.Immutable

@Immutable
data class SearchState(
    val text: String = "",
    val enabled: Boolean = true
)