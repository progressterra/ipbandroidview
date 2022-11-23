package com.progressterra.ipbandroidview.ui.pickuppoint

sealed class PickUpPointEffect {

    object Next : PickUpPointEffect()

    object Back : PickUpPointEffect()
}
