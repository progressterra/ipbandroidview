package com.progressterra.ipbandroidview.processes.partner

import com.progressterra.ipbandroidapi.api.collaboration.models.RFShop
import com.progressterra.ipbandroidapi.api.collaboration.models.RGEnterpriseData
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.shared.TripleMapper
import com.progressterra.ipbandroidview.entities.Offer
import com.progressterra.ipbandroidview.entities.Partner

interface PartnerMapper : TripleMapper<RGEnterpriseData, RFShop, List<Offer>, Partner> {

    class Base(manageResources: ManageResources) : PartnerMapper {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data1: RGEnterpriseData, data2: RFShop, data3: List<Offer>): Partner =
            Partner(
                id = data1.idUnique!!,
                title = data1.title ?: noData,
                description = data1.fullDescription ?: noData,
                offerList = data3,
                webSite = data1.websiteURL ?: noData,
                phone = data2.contacts ?: noData,
                headImageUrl = data1.headImageURL ?: noData,
                logoImageUrl = data1.logoImageURL ?: noData
            )
    }
}