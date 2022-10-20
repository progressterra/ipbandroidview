package com.progressterra.ipbandroidview.ui.photo

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.FileExplorer
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class PhotoViewModel(private val fileExplorer: FileExplorer) : ViewModel(),
    ContainerHost<PhotoState, PhotoEffect>, PhotoInteractor {

    override val container: Container<PhotoState, PhotoEffect> = container(PhotoState(null, true))

    @Suppress("unused")
    fun setImage(id: String, readOnly: Boolean) = intent {
        reduce { PhotoState(fileExplorer.obtainPictureAsBitmap(id), readOnly = readOnly) }
    }

    override fun back() = intent {
        postSideEffect(PhotoEffect.Back)
    }

    override fun remove() = intent {
        postSideEffect(PhotoEffect.Remove)
    }
}