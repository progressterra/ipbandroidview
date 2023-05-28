package com.progressterra.ipbandroidview.features.avatar

import androidx.compose.runtime.Immutable

@Immutable
data class AvatarState(
    val id: String = "",
    val url: String = "",
    val online: Boolean = false
)