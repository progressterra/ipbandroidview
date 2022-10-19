package com.progressterra.ipbandroidview.ui.photo

import com.progressterra.ipbandroidview.actions.Back

interface PhotoInteractor : Back {

    fun remove()

    class Empty : PhotoInteractor {

        override fun back() = Unit

        override fun remove() = Unit
    }
}