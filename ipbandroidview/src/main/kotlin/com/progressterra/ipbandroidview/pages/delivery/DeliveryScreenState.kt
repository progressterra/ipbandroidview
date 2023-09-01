package com.progressterra.ipbandroidview.pages.delivery

import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.features.addresssuggestions.SuggestionUI
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.widgets.deliverypicker.DeliveryPickerState


data class DeliveryScreenState(
    val deliveryPicker: DeliveryPickerState = DeliveryPickerState(),
    val commentary: TextFieldState = TextFieldState(id = "commentary"),
    val confirm: ButtonState = ButtonState(id = "confirm"),
    val address: AddressUI = AddressUI(),
    val suggestion: SuggestionUI = SuggestionUI(),
    val screen: StateColumnState = StateColumnState()
)