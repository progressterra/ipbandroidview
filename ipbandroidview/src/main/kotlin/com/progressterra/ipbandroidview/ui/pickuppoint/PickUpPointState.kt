package com.progressterra.ipbandroidview.ui.pickuppoint

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.model.delivery.PickUpPointInfo

@Immutable
data class PickUpPointState(
    val isPermissionGranted: Boolean = false,
    val pickUpPoints: List<PickUpPointInfo> = emptyList(),
    val currentPickUpPointInfo: PickUpPointInfo? = null
)