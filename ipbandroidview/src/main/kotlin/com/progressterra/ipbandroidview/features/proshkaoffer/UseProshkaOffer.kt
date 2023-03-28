package com.progressterra.ipbandroidview.features.proshkaoffer

interface UseProshkaOffer {

    fun handle(event: ProshkaOfferEvent)

    class Empty : UseProshkaOffer {
        override fun handle(event: ProshkaOfferEvent) = Unit
    }
}