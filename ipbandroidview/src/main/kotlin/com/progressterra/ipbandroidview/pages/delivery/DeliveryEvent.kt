package com.progressterra.ipbandroidview.pages.delivery

import com.progressterra.ipbandroidview.entities.PickUpPointInfo

sealed class DeliveryEvent {

    object Back : DeliveryEvent()

    object Next : DeliveryEvent()

    class SelectPickupPoint(val points: List<PickUpPointInfo>) : DeliveryEvent()
}
