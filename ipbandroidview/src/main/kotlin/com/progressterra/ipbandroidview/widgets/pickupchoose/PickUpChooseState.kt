package com.progressterra.ipbandroidview.widgets.pickupchoose

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.PickUpPointInfo

@Immutable
data class PickUpChooseState(
    val isPermissionGranted: Boolean = false,
    val pickUpPoints: List<PickUpPointInfo> = emptyList(),
    val currentPickUpPointInfo: PickUpPointInfo? = null
) {

    fun updatePermission(isGranted: Boolean) = copy(isPermissionGranted = isGranted)

    fun updatePoints(newPoints: List<PickUpPointInfo>) = copy(pickUpPoints = newPoints)
}