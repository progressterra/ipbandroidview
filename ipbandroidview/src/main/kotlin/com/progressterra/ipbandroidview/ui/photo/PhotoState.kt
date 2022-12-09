package com.progressterra.ipbandroidview.ui.photo

import com.progressterra.ipbandroidview.model.media.MultisizedImage

data class PhotoState(
    val picture: MultisizedImage? = null,
    val enabled: Boolean = false
)
