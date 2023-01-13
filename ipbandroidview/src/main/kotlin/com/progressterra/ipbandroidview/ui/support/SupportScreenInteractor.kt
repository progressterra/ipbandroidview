package com.progressterra.ipbandroidview.ui.support

interface SupportScreenInteractor {

    fun onBack()

    fun refresh()

    fun editMessage(message: String)

    fun sendMessage()

    class Empty : SupportScreenInteractor {

        override fun onBack() = Unit

        override fun refresh() = Unit

        override fun editMessage(message: String) = Unit

        override fun sendMessage() = Unit
    }
}