package com.progressterra.ipbandroidview.features.addresssuggestions

import com.progressterra.ipbandroidview.entities.SuggestionUI


data class AddressSuggestionsState(
    val isVisible: Boolean = false,
    val suggestions: List<SuggestionUI> = emptyList()
)