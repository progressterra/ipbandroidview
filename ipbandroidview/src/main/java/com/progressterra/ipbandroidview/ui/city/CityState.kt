package com.progressterra.ipbandroidview.ui.city

import androidx.compose.runtime.Immutable

@Immutable
data class CityState(
    val isDataValid: Boolean = false,
    val address: String = "",
    val suggestions: List<Suggestion> = emptyList(),
    val markerData: MarkerData = MarkerData(),
    val isAddressInFocus: Boolean = false
)