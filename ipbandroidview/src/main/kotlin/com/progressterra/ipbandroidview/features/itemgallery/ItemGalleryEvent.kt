package com.progressterra.ipbandroidview.features.itemgallery

sealed class ItemGalleryEvent(val image: String) {

    class Open(image: String) : ItemGalleryEvent(image)
}