package com.progressterra.ipbandroidview.domain.mapper

import com.progressterra.ipbandroidapi.api.collaboration.models.RFShop
import com.progressterra.ipbandroidapi.api.collaboration.models.RGEnterpriseData
import com.progressterra.ipbandroidapi.api.collaboration.models.RGOffersExt
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.TripleMapper
import com.progressterra.ipbandroidview.model.partner.Partner

interface PartnerMapping : TripleMapper<RGEnterpriseData, RFShop, RGOffersExt, Partner> {

    class Base(manageResources: ManageResources) : PartnerMapping {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data1: RGEnterpriseData, data2: RFShop, data3: RGOffersExt): Partner =
            Partner(
                id = data1.idUnique!!,
                title = data1.title ?: noData,
                description = data1.fullDescription ?: noData,
                offerList = listOf(),
                webSite = data1.websiteURL ?: noData,
                phone = data2.contacts ?: noData,
                headImageUrl = data1.headImageURL ?: noData,
                logoImageUrl = data1.logoImageURL ?: noData,
                miniImageUrl = data1.miniImageURL ?: noData
            )
    }
}