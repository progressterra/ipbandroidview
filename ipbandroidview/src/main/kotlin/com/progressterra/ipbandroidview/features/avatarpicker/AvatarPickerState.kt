package com.progressterra.ipbandroidview.features.avatarpicker

import androidx.compose.runtime.Immutable

@Immutable
data class AvatarPickerState(
    val items: List<Item> = emptyList()
) {

    @Immutable
    data class Item(
        val id: String = "",
        val url: String = "",
        val selected: Boolean = false
    )
}