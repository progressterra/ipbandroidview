package com.progressterra.ipbandroidview.features.avatarpicker


data class AvatarPickerState(
    val items: List<Item> = emptyList()
) {


    data class Item(
        val id: String = "",
        val url: String = "",
        val selected: Boolean = false
    )
}