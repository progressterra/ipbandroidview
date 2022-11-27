package com.progressterra.ipbandroidview.ui.city

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.model.AddressUI
import com.progressterra.ipbandroidview.model.SuggestionUI

@Immutable
data class CityState(
    val isPermissionGranted: Boolean = false,
    val isDataValid: Boolean = false,
    val address: String = "",
    val addressUI: AddressUI = AddressUI(),
    val suggestions: List<SuggestionUI> = emptyList(),
    val isAddressInFocus: Boolean = false
)