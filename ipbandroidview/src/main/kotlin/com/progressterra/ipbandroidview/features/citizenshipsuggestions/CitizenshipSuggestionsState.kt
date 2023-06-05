package com.progressterra.ipbandroidview.features.citizenshipsuggestions

import androidx.compose.runtime.Immutable


@Immutable
data class CitizenshipSuggestionsState(
    val items: List<Item> = emptyList()
) {

    @Immutable
    data class Item(
        val name: String,
        val data: String
    )
}

