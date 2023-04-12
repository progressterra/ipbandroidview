package com.progressterra.ipbandroidview.features.catalogcard

interface UseCatalogCard {

    fun handle(event: CatalogCardEvent)

    class Empty : UseCatalogCard {

        override fun handle(event: CatalogCardEvent) = Unit
    }
}