package com.progressterra.ipbandroidview.ui.pickuppoint

import com.progressterra.ipbandroidview.entities.PickUpPointInfo

sealed class PickUpPointEffect {

    class Next(val info: PickUpPointInfo) : PickUpPointEffect()

    object Back : PickUpPointEffect()
}
