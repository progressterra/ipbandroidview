package com.progressterra.ipbandroidview.pages.photo

import com.progressterra.ipbandroidview.features.phototopbar.PhotoTopBarEvent
import com.progressterra.ipbandroidview.shared.BaseViewModel

class PhotoViewModel : BaseViewModel<PhotoState, PhotoEvent>(), UsePhoto {

    override fun createInitialState() = PhotoState()

    fun setup(
        picture: String
    ) {
        emitState { PhotoState(picture) }
    }

    override fun handle(event: PhotoTopBarEvent) {
        postEffect(PhotoEvent.Back)
    }
}