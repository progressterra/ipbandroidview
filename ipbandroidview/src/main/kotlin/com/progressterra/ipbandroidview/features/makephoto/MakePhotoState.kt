package com.progressterra.ipbandroidview.features.makephoto

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.processors.IpbSubState

@Immutable
data class MakePhotoState(
    @IpbSubState val makePhoto: ButtonState = ButtonState(
        id = "makePhoto"
    ), val items: List<MultisizedImage> = emptyList()
) {

    fun add(item: MultisizedImage) = copy(items = items + item)

    fun remove(item: MultisizedImage) = copy(items = items - item)
}