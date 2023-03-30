package com.progressterra.ipbandroidview.widgets.proshkaoffers

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Offer
import com.progressterra.ipbandroidview.features.proshkaoffer.ProshkaOfferState

@Immutable
data class ProshkaOffersState(
    val items: List<ProshkaOfferState> = emptyList()
) {
    constructor(data: List<Offer>) : this(
        items = data.map {
            ProshkaOfferState(
                id = it.id,
                title = it.headDescription,
                image = it.imageUrl
            )
        }
    )
}