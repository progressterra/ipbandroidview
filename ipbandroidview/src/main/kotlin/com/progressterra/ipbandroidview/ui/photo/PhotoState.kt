package com.progressterra.ipbandroidview.ui.photo

import com.progressterra.ipbandroidview.model.MultisizedImage

data class PhotoState(
    val picture: MultisizedImage? = null,
    val enabled: Boolean = false
)
