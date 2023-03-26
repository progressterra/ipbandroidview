package com.progressterra.ipbandroidview.features.proshkatopbar

interface UseProshkaTopBar {

    fun handleEvent(id: String, event: ProshkaTopBarEvent) = Unit

    class Empty : UseProshkaTopBar {

        override fun handleEvent(id: String, event: ProshkaTopBarEvent) = Unit
    }
}