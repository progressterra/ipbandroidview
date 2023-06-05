package com.progressterra.ipbandroidview.features.citizenshipsuggestions

import androidx.compose.runtime.Immutable


@Immutable
data class CitizenshipSuggestionsState(
    val suggestion: String = "",
    val id: String = "",
    val toHide: Boolean = true
)

