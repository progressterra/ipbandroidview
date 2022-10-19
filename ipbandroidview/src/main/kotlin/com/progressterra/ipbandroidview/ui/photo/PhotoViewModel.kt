package com.progressterra.ipbandroidview.ui.photo

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class PhotoViewModel : ViewModel(), ContainerHost<PhotoState, PhotoEffect>, PhotoInteractor {

    override val container: Container<PhotoState, PhotoEffect> = container(PhotoState(null))

    fun setImage()

    override fun back() {
        TODO("Not yet implemented")
    }

    override fun remove() {
        TODO("Not yet implemented")
    }
}