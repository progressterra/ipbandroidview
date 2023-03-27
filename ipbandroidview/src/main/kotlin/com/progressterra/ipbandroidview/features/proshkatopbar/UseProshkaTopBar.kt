package com.progressterra.ipbandroidview.features.proshkatopbar

interface UseProshkaTopBar {

    fun handle(id: String, event: ProshkaTopBarEvent) = Unit

    class Empty : UseProshkaTopBar {

        override fun handle(id: String, event: ProshkaTopBarEvent) = Unit
    }
}