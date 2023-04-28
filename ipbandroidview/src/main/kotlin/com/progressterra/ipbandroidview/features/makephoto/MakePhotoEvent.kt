package com.progressterra.ipbandroidview.features.makephoto

sealed class MakePhotoEvent(val id: String) {

    class Select(id: String) : MakePhotoEvent(id)

    class Remove(id: String) : MakePhotoEvent(id)
}