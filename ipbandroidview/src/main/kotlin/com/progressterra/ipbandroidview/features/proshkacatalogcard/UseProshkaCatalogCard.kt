package com.progressterra.ipbandroidview.features.proshkacatalogcard

interface UseProshkaCatalogCard {

    fun handleEvent(id: String, event: ProshkaCatalogCardEvent)

    class Empty : UseProshkaCatalogCard {

        override fun handleEvent(id: String, event: ProshkaCatalogCardEvent) = Unit
    }
}