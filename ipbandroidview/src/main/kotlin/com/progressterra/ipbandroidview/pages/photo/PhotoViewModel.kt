package com.progressterra.ipbandroidview.pages.photo

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.phototopbar.PhotoTopBarEvent
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class PhotoViewModel : ViewModel(), ContainerHost<PhotoState, PhotoEvent>, UsePhoto {

    override val container: Container<PhotoState, PhotoEvent> = container(PhotoState())

    fun setup(
        picture: String
    ) {
        blockingIntent {
            reduce { PhotoState(picture) }
        }
    }

    override fun handle(event: PhotoTopBarEvent) {
        intent {
            postSideEffect(PhotoEvent.Back)
        }
    }
}