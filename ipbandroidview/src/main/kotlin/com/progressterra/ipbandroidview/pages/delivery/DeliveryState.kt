package com.progressterra.ipbandroidview.pages.delivery

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.entities.Delivery
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import com.progressterra.ipbandroidview.widgets.deliverypicker.DeliveryPickerState

@Immutable
data class DeliveryState(
    val stateBoxState: StateBoxState = StateBoxState(),
    val deliveryPickerState: DeliveryPickerState = DeliveryPickerState(),
    val confirm: ButtonState = ButtonState(id = "confirm")
) {

    fun updateStateBoxState(screenState: ScreenState) =
        copy(stateBoxState = stateBoxState.updateState(screenState))

    fun updateDeliveryPickerState(deliveryPickerState: DeliveryPickerState) =
        copy(deliveryPickerState = deliveryPickerState)

    fun updateDeliveryMethod(deliveryMethod: Delivery) =
        copy(deliveryPickerState = deliveryPickerState.updateSelectedMethod(deliveryMethod))

    fun updateCity(newCity: String) =
        copy(deliveryPickerState = deliveryPickerState.updateCity(newCity))

    fun updateHome(newHome: String) =
        copy(deliveryPickerState = deliveryPickerState.updateHome(newHome))

    fun updateEntrance(newEntrance: String) =
        copy(deliveryPickerState = deliveryPickerState.updateEntrance(newEntrance))

    fun updateApartment(newApartment: String) =
        copy(deliveryPickerState = deliveryPickerState.updateApartment(newApartment))
}