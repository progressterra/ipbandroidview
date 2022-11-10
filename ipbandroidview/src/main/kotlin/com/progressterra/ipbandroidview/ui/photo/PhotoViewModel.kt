package com.progressterra.ipbandroidview.ui.photo

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.model.CheckPicture
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class PhotoViewModel : ViewModel(),
    ContainerHost<PhotoState, PhotoEffect>, PhotoInteractor {

    override val container: Container<PhotoState, PhotoEffect> = container(PhotoState())

    @Suppress("unused")
    fun setPhoto(
        picture: CheckPicture,
        enabled: Boolean
    ) = intent {
        reduce {
            PhotoState(
                picture = picture, enabled = enabled
            )
        }
    }

    override fun back() = intent {
        postSideEffect(PhotoEffect.Back)
    }

    override fun remove() = intent {
        postSideEffect(PhotoEffect.Remove)
        postSideEffect(PhotoEffect.Back)
    }
}