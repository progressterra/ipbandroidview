package com.progressterra.ipbandroidview.city

import androidx.compose.runtime.Immutable

@Immutable
data class CityState(
    val isDataValid: Boolean = false,
    val address: String = "",
    val suggestions: List<Suggestion> = emptyList(),
    val marker: CurrentMarker = CurrentMarker()
)
