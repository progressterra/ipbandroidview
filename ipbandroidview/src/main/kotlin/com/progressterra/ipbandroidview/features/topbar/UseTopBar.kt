package com.progressterra.ipbandroidview.features.topbar

interface UseTopBar {

    fun handle(event: TopBarEvent)

    class Empty : UseTopBar {

        override fun handle(event: TopBarEvent) = Unit
    }
}
