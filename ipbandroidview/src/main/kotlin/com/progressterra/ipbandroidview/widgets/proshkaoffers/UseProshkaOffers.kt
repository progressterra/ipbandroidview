package com.progressterra.ipbandroidview.widgets.proshkaoffers

import com.progressterra.ipbandroidview.features.proshkaoffer.ProshkaOfferEvent
import com.progressterra.ipbandroidview.features.proshkaoffer.UseProshkaOffer

interface UseProshkaOffers : UseProshkaOffer {

    class Empty : UseProshkaOffers {

        override fun handle(id: String, event: ProshkaOfferEvent) = Unit
    }
}