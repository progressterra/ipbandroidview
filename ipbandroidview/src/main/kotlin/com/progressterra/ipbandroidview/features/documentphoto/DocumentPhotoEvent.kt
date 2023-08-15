package com.progressterra.ipbandroidview.features.documentphoto

import com.progressterra.ipbandroidview.entities.MultisizedImage

sealed class DocumentPhotoEvent {

    class Select(val image: MultisizedImage) : DocumentPhotoEvent()

    data object MakePhoto : DocumentPhotoEvent()
}