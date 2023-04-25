package com.progressterra.ipbandroidview.pages.pickuppoint

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.PickUpPointInfo
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.widgets.pickupchoose.PickUpChooseState

@Immutable
data class PickUpPointState(
    val choose: PickUpChooseState = PickUpChooseState(),
    val confirm: ButtonState = ButtonState()
) {

    fun uCurrentPoint(newPoint: PickUpPointInfo) = copy(choose = choose.uCurrentPoint(newPoint))

    fun uPermission(isGranted: Boolean) = copy(choose = choose.uPermission(isGranted))

    fun uPoints(newPoints: List<PickUpPointInfo>) = copy(choose = choose.uPoints(newPoints))
}