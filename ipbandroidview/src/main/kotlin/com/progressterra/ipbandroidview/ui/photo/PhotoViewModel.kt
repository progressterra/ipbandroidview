package com.progressterra.ipbandroidview.ui.photo

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.model.MultisizedImage
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class PhotoViewModel : ViewModel(),
    ContainerHost<PhotoState, PhotoEffect>, PhotoInteractor {

    override val container: Container<PhotoState, PhotoEffect> = container(PhotoState())

    fun setPhoto(
        picture: MultisizedImage,
        enabled: Boolean
    ) = intent {
        reduce {
            PhotoState(
                picture = picture, enabled = enabled
            )
        }
    }

    override fun onBack() {
        intent {
            postSideEffect(PhotoEffect.Back)
        }
    }

    override fun remove() {
        intent {
            postSideEffect(PhotoEffect.Remove)
            postSideEffect(PhotoEffect.Back)
        }
    }
}