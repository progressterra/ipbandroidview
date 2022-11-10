package com.progressterra.ipbandroidview.ui.city

import androidx.compose.runtime.Immutable

@Immutable
data class CityState(
    val isPermissionGranted: Boolean = false,
    val isDataValid: Boolean = false,
    val address: String = "",
    val suggestions: List<Suggestion> = emptyList(),
    val isAddressInFocus: Boolean = false
)