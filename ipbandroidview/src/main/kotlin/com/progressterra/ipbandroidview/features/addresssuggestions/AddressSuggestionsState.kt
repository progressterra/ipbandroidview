package com.progressterra.ipbandroidview.features.addresssuggestions

import androidx.compose.runtime.Immutable
import arrow.optics.optics

@Immutable
@optics
data class AddressSuggestionsState(
    val isVisible: Boolean = false,
    val suggestions: List<SuggestionUI> = emptyList()
) { companion object }