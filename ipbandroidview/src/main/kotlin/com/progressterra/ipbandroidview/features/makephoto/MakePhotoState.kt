package com.progressterra.ipbandroidview.features.makephoto

import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState


data class MakePhotoState(
    val makePhoto: ButtonState = ButtonState(),
    val items: List<MultisizedImage> = emptyList(),
    val enabled: Boolean = true
)