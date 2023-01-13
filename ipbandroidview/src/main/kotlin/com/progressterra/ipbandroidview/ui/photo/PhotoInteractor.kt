package com.progressterra.ipbandroidview.ui.photo

interface PhotoInteractor {

    fun onBack()

    fun remove()

    class Empty : PhotoInteractor {

        override fun onBack() = Unit

        override fun remove() = Unit
    }
}