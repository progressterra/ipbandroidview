package com.progressterra.ipbandroidview.features.proshkaoffer

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Offer

@Immutable
data class ProshkaOfferState(
    val id: String = "",
    val title: String = "",
    val image: String = ""
) {

    constructor(offer: Offer) : this(
        id = offer.id,
        title = offer.headDescription,
        image = offer.imageUrl
    )
}