package com.progressterra.ipbandroidview.pages.delivery

sealed class DeliveryEvent {

    data object Back : DeliveryEvent()

    data object Next : DeliveryEvent()
}
