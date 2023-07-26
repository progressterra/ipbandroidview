package com.progressterra.ipbandroidview.widgets.deliverypicker

import androidx.compose.runtime.Immutable
import arrow.optics.optics
import com.progressterra.ipbandroidview.features.addresssuggestions.AddressSuggestionsState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Immutable
@optics data class DeliveryPickerState(
    val address: TextFieldState = TextFieldState(id = "address"),
    val suggestions: AddressSuggestionsState = AddressSuggestionsState()
) { companion object }