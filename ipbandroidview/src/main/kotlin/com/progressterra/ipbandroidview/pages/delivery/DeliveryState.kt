package com.progressterra.ipbandroidview.pages.delivery

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.widgets.deliverypicker.DeliveryPickerState
import com.progressterra.ipbandroidview.widgets.deliverypicker.uApartmentText
import com.progressterra.ipbandroidview.widgets.deliverypicker.uCityText
import com.progressterra.ipbandroidview.widgets.deliverypicker.uEntranceText
import com.progressterra.ipbandroidview.widgets.deliverypicker.uHomeText
import com.progressterra.processors.IpbSubState

@Immutable
data class DeliveryState(
    val deliveryPicker: DeliveryPickerState = DeliveryPickerState(),
    @IpbSubState val commentary: TextFieldState = TextFieldState(
        id = "commentary"
    ),
    @IpbSubState val confirm: ButtonState = ButtonState(
        id = "confirm"
    )
) {

    fun uDeliveryPickerCityText(newText: String) =
        copy(deliveryPicker = deliveryPicker.uCityText(newText))

    fun uDeliveryPickerHomeText(newText: String) =
        copy(deliveryPicker = deliveryPicker.uHomeText(newText))

    fun uDeliveryPickerEntranceText(newText: String) =
        copy(deliveryPicker = deliveryPicker.uEntranceText(newText))

    fun uDeliveryPickerApartmentText(newText: String) =
        copy(deliveryPicker = deliveryPicker.uApartmentText(newText))
}