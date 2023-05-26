package com.progressterra.ipbandroidview.features.interests

import androidx.compose.runtime.Immutable

@Immutable
data class InterestsState(
    val items: List<Item> = emptyList(),
    val editMode: Boolean = false
) {

    @Immutable
    data class Item(
        val id: String = "",
        val name: String = "",
        val selected: Boolean = false
    )
}
