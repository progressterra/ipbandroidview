package com.progressterra.ipbandroidview.widgets.deliverypicker

import com.progressterra.ipbandroidview.features.addresssuggestions.AddressSuggestionsState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState


data class DeliveryPickerState(
    val address: TextFieldState = TextFieldState(id = "address"),
    val suggestions: AddressSuggestionsState = AddressSuggestionsState()
)