package com.progressterra.ipbandroidview.features.interests


data class InterestsState(
    val items: List<Item> = emptyList(),
    val editMode: Boolean = false
) {


    data class Item(
        val id: String = "",
        val name: String = "",
        val selected: Boolean = false
    )
}
