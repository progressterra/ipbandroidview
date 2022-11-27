package com.progressterra.ipbandroidview.ui.pickuppoint

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.model.SuggestionUI

@Immutable
data class PickUpPointState(
    val isPermissionGranted: Boolean = false,
    val isDataValid: Boolean = false,
    val address: String = "",
    val suggestions: List<SuggestionUI> = emptyList(),
    val isAddressInFocus: Boolean = false
)