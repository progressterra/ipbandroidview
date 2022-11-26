package com.progressterra.ipbandroidview.domain.mapper

import com.progressterra.ipbandroidapi.api.ipbdelivery.models.DeliveryMethod
import com.progressterra.ipbandroidapi.api.ipbdelivery.models.PaymentTypeEnum
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.Mapper
import com.progressterra.ipbandroidview.model.DeliveryPaymentType
import com.progressterra.ipbandroidview.model.PickUpPointInfo
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
                pickUpPointInfo = data.dhPickupPointInfo?.run {
                    PickUpPointInfo(
                        drrdAddress ?: "",
                        drrdSite ?: "",
                        drrdWorkHour ?: "",
                        drrfLatitude ?: 0.0,
                        drrfLongitude ?: 0.0,
                        drrfPickupPointCode ?: "",
                        drrfType ?: "",
                        fittingAvailable = drrdDressingRoom ?: false,
                        sendingParcels = drrdCanTake ?: false,
                        path = drrdWhereIs ?: "",
                        nearMetroStation = drrdMetroStation ?: "",
                        deliveryPaymentType = drrfPaymentAvaliable?.let { payment ->
                            when (payment) {
                                PaymentTypeEnum.ZERO -> DeliveryPaymentType.PAYMENT_NOT_AVAILABLE
                                PaymentTypeEnum.ONE -> DeliveryPaymentType.CASH
                                PaymentTypeEnum.TWO -> DeliveryPaymentType.CARD
                                PaymentTypeEnum.THREE -> DeliveryPaymentType.ALL_PAYMENT_TYPE
                            }
                        } ?: DeliveryPaymentType.PAYMENT_NOT_AVAILABLE
                    )
                },
                deliveryTypeText = data.rfDeliveryType ?: "",
                type = data.dhPickupPointInfo?.drrfType!!.toAddressId(),
                deliveryTime = data.rdDeliveryTime ?: noData,
                price = data.rdPrice?.let { priceMapper.map(it) } ?: SimplePrice(),
            )
    }
}