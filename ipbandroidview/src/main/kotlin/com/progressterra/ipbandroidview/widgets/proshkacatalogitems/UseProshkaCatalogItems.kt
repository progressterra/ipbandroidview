package com.progressterra.ipbandroidview.widgets.proshkacatalogitems

import com.progressterra.ipbandroidview.features.proshkacatalogcard.ProshkaCatalogCardEvent
import com.progressterra.ipbandroidview.features.proshkacatalogcard.UseProshkaCatalogCard
import com.progressterra.ipbandroidview.widgets.proshkastoreitems.UseProshkaCatalogItems

interface UseProshkaCatalogItems : UseProshkaCatalogCard {

    class Empty : UseProshkaCatalogItems {

        override fun handle(event: ProshkaCatalogCardEvent) = Unit
    }
}