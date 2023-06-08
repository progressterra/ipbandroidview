package com.progressterra.ipbandroidview.features.currentcitizenship

interface UseCurrentCitizenship {

    fun handle(event: CurrentCitizenshipEvent)

    class Empty : UseCurrentCitizenship {

        override fun handle(event: CurrentCitizenshipEvent) = Unit
    }
}