package com.progressterra.ipbandroidview.features.documentphoto

interface UseDocumentPhoto {

    fun handle(event: DocumentPhotoEvent)

    class Empty : UseDocumentPhoto {

        override fun handle(event: DocumentPhotoEvent) = Unit
    }
}