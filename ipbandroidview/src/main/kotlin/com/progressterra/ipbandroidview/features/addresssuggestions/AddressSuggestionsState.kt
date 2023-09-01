package com.progressterra.ipbandroidview.features.addresssuggestions


data class AddressSuggestionsState(
    val isVisible: Boolean = false,
    val suggestions: List<SuggestionUI> = emptyList()
)