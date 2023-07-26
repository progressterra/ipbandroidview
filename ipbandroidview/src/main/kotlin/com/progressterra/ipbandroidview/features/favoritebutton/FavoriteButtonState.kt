package com.progressterra.ipbandroidview.features.favoritebutton

import androidx.compose.runtime.Immutable
import arrow.optics.optics

@Immutable
@optics data class FavoriteButtonState(
    val id: String = "",
    val enabled: Boolean = true,
    val favorite: Boolean = false
) { companion object }
