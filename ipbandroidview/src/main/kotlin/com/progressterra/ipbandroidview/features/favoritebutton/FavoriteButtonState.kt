package com.progressterra.ipbandroidview.features.favoritebutton

import androidx.compose.runtime.Immutable
import com.progressterra.processors.IpbState

@Immutable
@IpbState
data class FavoriteButtonState(
    val id: String = "",
    val enabled: Boolean = true,
    val favorite: Boolean = false
)
