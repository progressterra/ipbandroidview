package com.progressterra.ipbandroidview.ui.photo

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.model.CheckPicture

@Immutable
data class PhotoState(
    val picture: CheckPicture? = null,
    val enabled: Boolean = false
)
