package com.progressterra.ipbandroidview.features.proshkacatalogcard

interface UseProshkaCatalogCard {

    fun handle(id: String, event: ProshkaCatalogCardEvent)

    class Empty : UseProshkaCatalogCard {

        override fun handle(id: String, event: ProshkaCatalogCardEvent) = Unit
    }
}