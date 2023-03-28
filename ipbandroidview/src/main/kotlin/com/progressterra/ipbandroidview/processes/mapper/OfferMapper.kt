package com.progressterra.ipbandroidview.processes.mapper

import com.progressterra.ipbandroidapi.api.collaboration.models.RGOffersExt
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.Mapper
import com.progressterra.ipbandroidview.entities.OfferUI

interface OfferMapper : Mapper<RGOffersExt, OfferUI> {

    class Base(
        manageResources: ManageResources
    ) : OfferMapper {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data: RGOffersExt): OfferUI = OfferUI(
            id = data.idUnique!!,
            headDescription = data.headDescription ?: noData,
            fullDescription = data.fullDescription ?: noData,
            imageUrl = data.imageUrl ?: ""
        )
    }
}