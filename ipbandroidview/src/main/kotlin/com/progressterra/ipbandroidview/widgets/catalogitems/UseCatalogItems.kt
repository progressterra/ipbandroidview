package com.progressterra.ipbandroidview.widgets.catalogitems

import com.progressterra.ipbandroidview.features.proshkacatalogcard.ProshkaCatalogCardEvent
import com.progressterra.ipbandroidview.features.proshkacatalogcard.UseProshkaCatalogCard

interface UseCatalogItems : UseProshkaCatalogCard {

    class Empty : UseCatalogItems {

        override fun handle(event: ProshkaCatalogCardEvent) = Unit
    }
}