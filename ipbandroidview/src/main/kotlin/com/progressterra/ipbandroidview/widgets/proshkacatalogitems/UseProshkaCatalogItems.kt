package com.progressterra.ipbandroidview.widgets.proshkacatalogitems

import com.progressterra.ipbandroidview.features.proshkacatalogcard.ProshkaCatalogCardEvent
import com.progressterra.ipbandroidview.features.proshkacatalogcard.UseProshkaCatalogCard

interface UseProshkaCatalogItems : UseProshkaCatalogCard {

    class Empty : UseProshkaCatalogItems {

        override fun handle(id: String, event: ProshkaCatalogCardEvent) = Unit
    }
}