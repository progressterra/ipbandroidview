package com.progressterra.ipbandroidview.widgets.offers

import com.progressterra.ipbandroidapi.api.collaboration.models.RGOffersExt
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.offer.OfferState
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.shared.Mapper

interface OfferMapper : Mapper<RGOffersExt, OfferState> {

    class Base(
        manageResources: ManageResources
    ) : OfferMapper {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data: RGOffersExt): OfferState = OfferState(
            title = data.headDescription ?: noData,
            image = data.imageUrl ?: ""
        )
    }
}