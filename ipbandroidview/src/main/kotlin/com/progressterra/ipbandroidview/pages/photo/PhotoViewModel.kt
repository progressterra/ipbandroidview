package com.progressterra.ipbandroidview.pages.photo

import com.progressterra.ipbandroidview.features.phototopbar.PhotoTopBarEvent
import com.progressterra.ipbandroidview.shared.mvi.BaseViewModel

class PhotoViewModel : BaseViewModel<PhotoState, PhotoEvent>(), UsePhoto {

    override fun createInitialState() = PhotoState()

    fun setup(
        picture: String
    ) {
        emitState { it.copy(picture = picture) }
    }

    override fun handle(event: PhotoTopBarEvent) {
        postEffect(PhotoEvent.Back)
    }
}