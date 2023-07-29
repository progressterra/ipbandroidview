package com.progressterra.ipbandroidview.features.addresssuggestions

import androidx.compose.runtime.Immutable

@Immutable
data class AddressSuggestionsState(
    val isVisible: Boolean = false,
    val suggestions: List<SuggestionUI> = emptyList()
)