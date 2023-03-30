package com.progressterra.ipbandroidview.processes.partner

import com.progressterra.ipbandroidapi.api.collaboration.models.RGOffersExt
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.Mapper
import com.progressterra.ipbandroidview.entities.Offer

interface OfferMapper : Mapper<RGOffersExt, Offer> {

    class Base(
        manageResources: ManageResources
    ) : OfferMapper {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data: RGOffersExt): Offer = Offer(
            id = data.idUnique!!,
            headDescription = data.headDescription ?: noData,
            fullDescription = data.fullDescription ?: noData,
            imageUrl = data.imageUrl ?: ""
        )
    }
}