package com.progressterra.ipbandroidview.pages.photo

import com.progressterra.ipbandroidview.features.phototopbar.PhotoTopBarEvent
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputViewModel

class PhotoScreenViewModel : AbstractInputViewModel<String, PhotoScreenState, PhotoScreenEffect>(),
    UsePhotoScreen {

    override fun createInitialState() = PhotoScreenState()

    override fun setup(data: String) {
        emitState { it.copy(picture = data) }
    }

    override fun handle(event: PhotoTopBarEvent) {
        postEffect(PhotoScreenEffect.Back)
    }
}