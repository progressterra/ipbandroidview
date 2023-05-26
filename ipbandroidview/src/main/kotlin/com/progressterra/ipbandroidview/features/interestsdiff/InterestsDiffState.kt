package com.progressterra.ipbandroidview.features.interestsdiff

import androidx.compose.runtime.Immutable

@Immutable
data class InterestsDiffState(
    val items: List<Item> = emptyList()
) {

    @Immutable
    data class Item(
        val id: String = "",
        val name: String = "",
        val match: Boolean = false
    )
}
