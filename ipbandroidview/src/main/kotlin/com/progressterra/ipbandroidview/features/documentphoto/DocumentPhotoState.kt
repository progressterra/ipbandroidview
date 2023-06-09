package com.progressterra.ipbandroidview.features.documentphoto

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.processors.IpbSubState

@Immutable
data class DocumentPhotoState(
    val items: List<MultisizedImage> = emptyList(),
    val docName: String = "",
    val enabled: Boolean = true
) {

    fun uEnabled(newEnabled: Boolean) = copy(enabled = newEnabled)

    fun add(item: MultisizedImage) = copy(items = items + item)

    fun remove(item: MultisizedImage) = copy(items = items - item)
}