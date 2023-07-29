package com.progressterra.ipbandroidview.features.goodsdescription

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.favoritebutton.FavoriteButtonState

@Immutable
data class GoodsDescriptionState(
    val name: String = "",
    val description: String = "",
    val properties: List<Pair<String, String>> = emptyList(),
    val favoriteButton: FavoriteButtonState = FavoriteButtonState()
) {
    companion object
}