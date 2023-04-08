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

    fun updatePermission(isGranted: Boolean) = copy(choose = choose.updatePermission(isGranted))

    fun updatePoints(newPoints: List<PickUpPointInfo>) = copy(choose = choose.updatePoints(newPoints))
}