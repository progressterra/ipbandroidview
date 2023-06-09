package com.progressterra.ipbandroidview.features.documentphoto

import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton

interface UseDocumentPhoto {

    fun handle(event: DocumentPhotoEvent)

    class Empty : UseDocumentPhoto {

        override fun handle(event: DocumentPhotoEvent) = Unit
    }
}