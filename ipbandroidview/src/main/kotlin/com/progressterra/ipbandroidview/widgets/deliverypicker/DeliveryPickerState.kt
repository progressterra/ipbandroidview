package com.progressterra.ipbandroidview.widgets.deliverypicker

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.addresssuggestions.AddressSuggestionsState
import com.progressterra.ipbandroidview.features.addresssuggestions.SuggestionUI
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.processors.IpbSubState

@Immutable
data class DeliveryPickerState(
    @IpbSubState val address: TextFieldState = TextFieldState(id = "address"),
    val suggestions: AddressSuggestionsState = AddressSuggestionsState()
) {

    fun uSuggestions(newSuggestions: List<SuggestionUI>) =
        copy(suggestions = suggestions.uSuggestions(newSuggestions))

    fun uSuggestionsVisible(newVisible: Boolean) =
        copy(suggestions = suggestions.uVisible(newVisible))
}