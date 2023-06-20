package com.progressterra.ipbandroidview.widgets.deliverypicker

sealed class DeliveryPickerEvent {

    class SelectDeliveryMethod(val delivery: Delivery) : DeliveryPickerEvent()
}