package com.progressterra.ipbandroidview.pages.delivery

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.features.addresssuggestions.SuggestionUI
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.widgets.deliverypicker.DeliveryPickerState
import com.progressterra.ipbandroidview.widgets.deliverypicker.uAddressText
import com.progressterra.processors.IpbSubState

@Immutable
data class DeliveryState(
    val deliveryPicker: DeliveryPickerState = DeliveryPickerState(),
    @IpbSubState val commentary: TextFieldState = TextFieldState(
        id = "commentary"
    ),
    @IpbSubState val confirm: ButtonState = ButtonState(
        id = "confirm"
    ),
    val address: AddressUI? = null
) {

    fun uSuggestions(newSuggestions: List<SuggestionUI>) =
        copy(deliveryPicker = deliveryPicker.uSuggestions(newSuggestions))

    fun uSuggestionsVisible(newVisible: Boolean) =
        copy(deliveryPicker = deliveryPicker.uSuggestionsVisible(newVisible))

    fun uDeliveryPickerAddressText(newAddress: String) =
        copy(deliveryPicker = deliveryPicker.uAddressText(newAddress))

    fun uAddress(newAddress: AddressUI?) = copy(address = newAddress)
}