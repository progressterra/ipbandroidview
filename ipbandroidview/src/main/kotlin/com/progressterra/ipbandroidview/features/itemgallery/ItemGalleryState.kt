package com.progressterra.ipbandroidview.features.itemgallery

import androidx.compose.runtime.Immutable

@Immutable
data class ItemGalleryState(
    val images: List<String> = emptyList()
) {
    companion object
}