package com.progressterra.ipbandroidview.pages.delivery

sealed class DeliveryScreenEffect {

    data object Back : DeliveryScreenEffect()

    data object Next : DeliveryScreenEffect()
}
