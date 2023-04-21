package com.progressterra.ipbandroidview.features.itemgallery

interface UseItemGallery {

    fun handle(event: ItemGalleryEvent)

    class Empty : UseItemGallery {

        override fun handle(event: ItemGalleryEvent) = Unit
    }
}