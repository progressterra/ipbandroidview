package com.progressterra.ipbandroidview.features.interestspicker


data class InterestsPickerState(
    val items: List<Item> = emptyList()
) {


    data class Item(
        val id: String = "",
        val name: String = "",
        val selected: Boolean = false
    )
}
