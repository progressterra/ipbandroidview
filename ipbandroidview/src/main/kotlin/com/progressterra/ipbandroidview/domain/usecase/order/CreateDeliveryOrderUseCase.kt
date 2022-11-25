package com.progressterra.ipbandroidview.domain.usecase.order

import com.progressterra.ipbandroidapi.api.ipbdelivery.IPBDeliveryRepository
import com.progressterra.ipbandroidapi.api.ipbdelivery.models.DeliverySeriviceTypeEnum
import com.progressterra.ipbandroidapi.api.ipbdelivery.models.RGDeliveryParams
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.model.DeliveryMethod

interface CreateDeliveryOrderUseCase {

    suspend fun create(comment: String, deliveryMethod: DeliveryMethod): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val repository: IPBDeliveryRepository
    ) : CreateDeliveryOrderUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun create(comment: String, deliveryMethod: DeliveryMethod): Result<Unit> =
            withToken { token ->
                repository.createDeliveryOrder(
                    setDeliveryTypeRequest = RGDeliveryParams(
                        rfMethodType = deliveryMethod.type.toServiceEnum(),
                        rfServiceType = DeliverySeriviceTypeEnum.ZERO,
                        rdPickUpPoint = deliveryMethod.pickUpPointInfo?.pickupPointCode,
                    ),
                    accessToken = token
                )
            }
    }
}