package com.progressterra.ipbandroidview.domain.mapper

import com.progressterra.ipbandroidapi.api.ipbdelivery.models.DeliveryMethod
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.Mapper
import com.progressterra.ipbandroidview.model.SimplePrice
import com.progressterra.ipbandroidview.model.toAddressId

interface DeliveryMethodMapper :
    Mapper<DeliveryMethod, com.progressterra.ipbandroidview.model.DeliveryMethod> {

    class Base(
        private val priceMapper: PriceMapper,
        manageResources: ManageResources
    ) : DeliveryMethodMapper {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data: DeliveryMethod): com.progressterra.ipbandroidview.model.DeliveryMethod =
            com.progressterra.ipbandroidview.model.DeliveryMethod(
                deliveryTypeText = data.rfDeliveryType ?: "",
                type = data.dhPickupPointInfo?.drrfType!!.toAddressId(),
                deliveryTime = data.rdDeliveryTime ?: noData,
                price = data.rdPrice?.let { priceMapper.map(it) } ?: SimplePrice(),
                addressNeeded = false
            )

    }
}