package com.progressterra.ipbandroidview.features.makephoto

import com.progressterra.ipbandroidview.entities.MultisizedImage

sealed class MakePhotoEvent(val photo: MultisizedImage) {

    class Select(image: MultisizedImage) : MakePhotoEvent(image)

    class Remove(image: MultisizedImage) : MakePhotoEvent(image)
}