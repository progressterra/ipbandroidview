package com.progressterra.ipbandroidview.domain.mapper

import com.progressterra.ipbandroidapi.api.ipbdelivery.models.DeliveryMethod
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidapi.ext.parseToDate
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.Mapper
import com.progressterra.ipbandroidview.model.Delivery
import com.progressterra.ipbandroidview.model.DeliveryMethodId
import com.progressterra.ipbandroidview.model.PickUpPointInfo
import com.progressterra.ipbandroidview.model.SimplePrice

interface DeliveryMethodMapper : Mapper<List<DeliveryMethod>, List<Delivery>> {

    class Base(
        private val priceMapper: PriceMapper, manageResources: ManageResources
    ) : DeliveryMethodMapper {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data: List<DeliveryMethod>): List<Delivery> = buildList {
            val pickPoints: List<PickUpPointInfo> = buildList {
                data.forEach {
                    when (it.dhPickupPointInfo?.drrfType) {
                        "Доставка до постомата" -> add(
                            PickUpPointInfo(address = it.dhPickupPointInfo?.drrdAddress
                                ?: "",
                                workHour = it.dhPickupPointInfo?.drrdWorkHour ?: "",
                                latitude = it.dhPickupPointInfo?.drrfLatitude ?: 0.0,
                                longitude = it.dhPickupPointInfo?.drrfLongitude ?: 0.0,
                                pickupPointCode = it.dhPickupPointInfo?.drrfPickupPointCode ?: "",
                                path = it.dhPickupPointInfo?.drrdWhereIs ?: "",
                                id = DeliveryMethodId.POSTAMAT,
                                price = it.rdPrice?.let { price -> priceMapper.map(price) }
                                    ?: SimplePrice(),
                                type = it.dhPickupPointInfo?.drrfType ?: noData
                            )
                        )
                        "Доставка до Пункта Выдачи Заказов (ПВЗ)" -> add(
                            PickUpPointInfo(address = it.dhPickupPointInfo?.drrdAddress
                                ?: "",
                                workHour = it.dhPickupPointInfo?.drrdWorkHour ?: "",
                                latitude = it.dhPickupPointInfo?.drrfLatitude ?: 0.0,
                                longitude = it.dhPickupPointInfo?.drrfLongitude ?: 0.0,
                                pickupPointCode = it.dhPickupPointInfo?.drrfPickupPointCode ?: "",
                                path = it.dhPickupPointInfo?.drrdWhereIs ?: "",
                                id = DeliveryMethodId.PVZ,
                                price = it.rdPrice?.let { price -> priceMapper.map(price) }
                                    ?: SimplePrice(),
                                type = it.dhPickupPointInfo?.drrfType ?: noData
                            )
                        )
                        else -> add(
                            Delivery.CourierDelivery(
                                id = DeliveryMethodId.COURIER,
                                date = it.rdDeliveryTime?.parseToDate()?.format("dd.MM") ?: noData,
                                price = it.rdPrice?.let { price -> priceMapper.map(price) }
                                    ?: SimplePrice(),
                                type = it.rfDeliveryType ?: noData
                            )
                        )
                    }
                }
            }
            add(
                Delivery.PickUpPointDelivery(
                    points = pickPoints,
                    currentPoint = pickPoints.firstOrNull() ?: PickUpPointInfo()
                )
            )
        }
    }
}