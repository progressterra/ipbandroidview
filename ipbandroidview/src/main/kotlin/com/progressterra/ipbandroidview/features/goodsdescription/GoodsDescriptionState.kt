package com.progressterra.ipbandroidview.features.goodsdescription

import com.progressterra.ipbandroidview.features.favoritebutton.FavoriteButtonState


data class GoodsDescriptionState(
    val name: String = "",
    val description: String = "",
    val properties: List<Pair<String, String>> = emptyList(),
    val favoriteButton: FavoriteButtonState = FavoriteButtonState()
) {
    companion object
}