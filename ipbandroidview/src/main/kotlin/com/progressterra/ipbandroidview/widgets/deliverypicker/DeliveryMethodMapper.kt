package com.progressterra.ipbandroidview.widgets.deliverypicker

import com.progressterra.ipbandroidapi.api.ipbdelivery.models.DeliveryMethod
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.Mapper
import com.progressterra.ipbandroidview.entities.Delivery
import com.progressterra.ipbandroidview.entities.DeliveryMethodId
import com.progressterra.ipbandroidview.entities.PickUpPointInfo
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.processes.mapper.PriceMapper

interface DeliveryMethodMapper : Mapper<DeliveryMethod, Delivery> {

    class Base(
        private val priceMapper: PriceMapper, manageResources: ManageResources
    ) : DeliveryMethodMapper {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data: DeliveryMethod): Delivery {
            val pickPoints: List<PickUpPointInfo> = buildList {
                when (data.dhPickupPointInfo?.drrfType) {
                    "Доставка до постомата" -> {
                        add(
                            PickUpPointInfo(address = data.dhPickupPointInfo?.drrdAddress
                                ?: "",
                                workHour = data.dhPickupPointInfo?.drrdWorkHour ?: "",
                                latitude = data.dhPickupPointInfo?.drrfLatitude ?: 0.0,
                                longitude = data.dhPickupPointInfo?.drrfLongitude ?: 0.0,
                                pickupPointCode = data.dhPickupPointInfo?.drrfPickupPointCode
                                    ?: "",
                                path = data.dhPickupPointInfo?.drrdWhereIs ?: "",
                                id = DeliveryMethodId.POSTAMAT,
                                price = data.rdPrice?.let { price -> priceMapper.map(price) }
                                    ?: SimplePrice(),
                                type = data.dhPickupPointInfo?.drrfType ?: noData
                            )
                        )
                    }
                    "Доставка до Пункта Выдачи Заказов (ПВЗ)" -> {
                        add(
                            PickUpPointInfo(address = data.dhPickupPointInfo?.drrdAddress
                                ?: "",
                                workHour = data.dhPickupPointInfo?.drrdWorkHour ?: "",
                                latitude = data.dhPickupPointInfo?.drrfLatitude ?: 0.0,
                                longitude = data.dhPickupPointInfo?.drrfLongitude ?: 0.0,
                                pickupPointCode = data.dhPickupPointInfo?.drrfPickupPointCode
                                    ?: "",
                                path = data.dhPickupPointInfo?.drrdWhereIs ?: "",
                                id = DeliveryMethodId.PVZ,
                                price = data.rdPrice?.let { price -> priceMapper.map(price) }
                                    ?: SimplePrice(),
                                type = data.dhPickupPointInfo?.drrfType ?: noData
                            )
                        )
                    }
                    else -> return Delivery.CourierDelivery(
                        id = DeliveryMethodId.COURIER,
                        price = data.rdPrice?.let { price -> priceMapper.map(price) }
                            ?: SimplePrice(),
                        type = data.rfDeliveryType ?: noData
                    )
                }
            }
            return Delivery.PickUpPointDelivery(
                points = pickPoints,
                currentPoint = pickPoints.firstOrNull() ?: PickUpPointInfo()
            )
        }
    }
}