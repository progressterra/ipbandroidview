package com.progressterra.ipbandroidview.pages.delivery

sealed class DeliveryEvent {

    object Back : DeliveryEvent()

    object Next : DeliveryEvent()

    class SelectPickupPoint(val points: List<PickUpPointInfo>) : DeliveryEvent()
}
