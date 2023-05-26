package com.progressterra.ipbandroidview.features.interests

interface UseInterests {

    fun handle(event: InterestsEvent)

    class Empty : UseInterests {

        override fun handle(event: InterestsEvent) = Unit
    }
}