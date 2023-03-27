package com.progressterra.ipbandroidview.features.proshkaoffer

interface UseProshkaOffer {

    fun handle(id: String, event: ProshkaOfferEvent)

    class Empty : UseProshkaOffer {
        override fun handle(id: String, event: ProshkaOfferEvent) = Unit
    }
}