package com.progressterra.ipbandroidview.features.itemgallery

import androidx.compose.runtime.Immutable
import arrow.optics.optics

@Immutable
@optics
data class ItemGalleryState(
    val images: List<String> = emptyList()
) {
    companion object
}