package com.progressterra.ipbandroidview.features.goodsdescription

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.favoritebutton.FavoriteButtonState

@Immutable
data class GoodsDescriptionState(
    val name: String = "",
    val description: String = "",
    val company: String = "",
    val favoriteButton: FavoriteButtonState = FavoriteButtonState()
)