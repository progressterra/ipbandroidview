package com.progressterra.ipbandroidview.features.bottombar

interface UseRediBottomBar {

    fun handle(event: RediBottomBarEvent)

    class Empty : UseRediBottomBar {

        override fun handle(event: RediBottomBarEvent) = Unit
    }
}