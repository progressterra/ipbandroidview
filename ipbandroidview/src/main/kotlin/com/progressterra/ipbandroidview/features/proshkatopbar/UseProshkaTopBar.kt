package com.progressterra.ipbandroidview.features.proshkatopbar

interface UseProshkaTopBar {

    fun handle(event: ProshkaTopBarEvent) = Unit

    class Empty : UseProshkaTopBar {

        override fun handle(event: ProshkaTopBarEvent) = Unit
    }
}