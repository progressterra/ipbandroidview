package com.progressterra.ipbandroidview.pages.pickuppoint

sealed class PickUpPointEffect {

    class Next(val info: PickUpPointInfo) : PickUpPointEffect()

    object Back : PickUpPointEffect()
}
