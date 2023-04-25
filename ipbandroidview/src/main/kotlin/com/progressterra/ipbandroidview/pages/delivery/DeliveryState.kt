package com.progressterra.ipbandroidview.pages.delivery

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Delivery
import com.progressterra.ipbandroidview.entities.PickUpPointInfo
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.widgets.deliverypicker.DeliveryPickerState

@Immutable
data class DeliveryState(
    val stateBox: ScreenState = ScreenState.LOADING,
    val deliveryPicker: DeliveryPickerState = DeliveryPickerState(),
    val commentary: TextFieldState = TextFieldState(
        id = "commentary"
    ),
    val confirm: ButtonState = ButtonState(
        id = "confirm"
    )
) {

    fun uStateBoxState(screenState: ScreenState) = copy(stateBox = screenState)

    fun uDeliveryPickerState(deliveryPickerState: DeliveryPickerState) =
        copy(deliveryPicker = deliveryPickerState)

    fun uPickUpPoint(newPickUpPointInfo: PickUpPointInfo) = copy(
        deliveryPicker = deliveryPicker.uPickUpPointInfo(newPickUpPointInfo)
    )

    fun uDeliveryMethod(deliveryMethod: Delivery) =
        copy(deliveryPicker = deliveryPicker.uSelectedMethod(deliveryMethod))

    fun uCity(newCity: String) = copy(deliveryPicker = deliveryPicker.uCity(newCity))

    fun uHome(newHome: String) = copy(deliveryPicker = deliveryPicker.uHome(newHome))

    fun uEntrance(newEntrance: String) =
        copy(deliveryPicker = deliveryPicker.uEntrance(newEntrance))

    fun uApartment(newApartment: String) =
        copy(deliveryPicker = deliveryPicker.uApartment(newApartment))

    fun uCommentary(newCommentary: String) =
        copy(commentary = commentary.uText(newCommentary))

    fun uConfirmEnabled(isEnabled: Boolean) = copy(confirm = confirm.uEnabled(isEnabled))
}