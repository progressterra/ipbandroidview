package com.progressterra.ipbandroidview.widgets.deliverypicker

import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.entities.Delivery

sealed class DeliveryPickerEvent {

    class ChangeAddress(val addressUI: AddressUI) : DeliveryPickerEvent()

    class SelectDeliveryMethod(val delivery: Delivery) : DeliveryPickerEvent()

    object SelectPickupPoint : DeliveryPickerEvent()
}