package com.progressterra.ipbandroidview.widgets.catalogitems

import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardEvent
import com.progressterra.ipbandroidview.features.catalogcard.UseCatalogCard

interface UseCatalogItems : UseCatalogCard {

    class Empty : UseCatalogItems {

        override fun handle(event: CatalogCardEvent) = Unit
    }
}