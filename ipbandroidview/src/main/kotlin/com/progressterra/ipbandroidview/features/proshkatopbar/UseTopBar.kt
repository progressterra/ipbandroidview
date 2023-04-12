package com.progressterra.ipbandroidview.features.proshkatopbar

interface UseTopBar {

    fun handle(event: ProshkaTopBarEvent) = Unit

    class Empty : UseTopBar {

        override fun handle(event: ProshkaTopBarEvent) = Unit
    }
}