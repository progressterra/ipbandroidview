package com.progressterra.ipbandroidview.ui.support

interface SupportInteractor {

    fun onBack()

    fun refresh()

    fun editMessage(message: String)

    fun sendMessage()

    class Empty : SupportInteractor {

        override fun onBack() = Unit

        override fun refresh() = Unit

        override fun editMessage(message: String) = Unit

        override fun sendMessage() = Unit
    }
}