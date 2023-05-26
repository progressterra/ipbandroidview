package com.progressterra.ipbandroidview.features.interestspicker

import androidx.compose.runtime.Immutable

@Immutable
data class InterestsPickerState(
    val items: List<Item> = emptyList()
) {

    @Immutable
    data class Item(
        val id: String = "",
        val name: String = "",
        val selected: Boolean = false
    )
}
