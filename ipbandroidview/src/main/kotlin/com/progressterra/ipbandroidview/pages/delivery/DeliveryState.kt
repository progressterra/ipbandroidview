package com.progressterra.ipbandroidview.pages.delivery

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Delivery
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.widgets.deliverypicker.DeliveryPickerState

@Immutable
data class DeliveryState(
    val stateBox: ScreenState = ScreenState.LOADING,
    val deliveryPicker: DeliveryPickerState = DeliveryPickerState(),
    val commentary: TextFieldState = TextFieldState(),
    val confirm: ButtonState = ButtonState()
) {

    fun updateStateBoxState(screenState: ScreenState) = copy(stateBox = screenState)

    fun updateDeliveryPickerState(deliveryPickerState: DeliveryPickerState) =
        copy(deliveryPicker = deliveryPickerState)

    fun updateDeliveryMethod(deliveryMethod: Delivery) =
        copy(deliveryPicker = deliveryPicker.updateSelectedMethod(deliveryMethod))

    fun updateCity(newCity: String) =
        copy(deliveryPicker = deliveryPicker.updateCity(newCity))

    fun updateHome(newHome: String) =
        copy(deliveryPicker = deliveryPicker.updateHome(newHome))

    fun updateEntrance(newEntrance: String) =
        copy(deliveryPicker = deliveryPicker.updateEntrance(newEntrance))

    fun updateApartment(newApartment: String) =
        copy(deliveryPicker = deliveryPicker.updateApartment(newApartment))

    fun updateCommentary(newCommentary: String) =
        copy(commentary = commentary.updateText(newCommentary))
}