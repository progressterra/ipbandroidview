package com.progressterra.ipbandroidview.features.proshkacatalogcard

interface UseProshkaCatalogCard {

    fun handle(event: ProshkaCatalogCardEvent)

    class Empty : UseProshkaCatalogCard {

        override fun handle(event: ProshkaCatalogCardEvent) = Unit
    }
}