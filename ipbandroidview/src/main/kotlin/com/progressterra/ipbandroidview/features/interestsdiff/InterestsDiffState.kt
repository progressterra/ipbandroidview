package com.progressterra.ipbandroidview.features.interestsdiff


data class InterestsDiffState(
    val items: List<Item> = emptyList()
) {


    data class Item(
        val id: String = "",
        val name: String = "",
        val match: Boolean = false
    )
}
