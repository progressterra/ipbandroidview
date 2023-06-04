package com.progressterra.ipbandroidview.pages.delivery

import com.progressterra.ipbandroidapi.api.ipbdelivery.IPBDeliveryRepository
import com.progressterra.ipbandroidapi.api.ipbdelivery.models.DeliverySeriviceTypeEnum
import com.progressterra.ipbandroidapi.api.ipbdelivery.models.RGDeliveryParams
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.Delivery
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface CreateDeliveryOrderUseCase {

    suspend operator fun invoke(comment: String, deliveryMethod: Delivery): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val repository: IPBDeliveryRepository
    ) : CreateDeliveryOrderUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(comment: String, deliveryMethod: Delivery): Result<Unit> =
            withToken { token ->
                repository.createDeliveryOrder(
                    setDeliveryTypeRequest = RGDeliveryParams(
                        rfMethodType = deliveryMethod.id.toServiceEnum(),
                        rfServiceType = DeliverySeriviceTypeEnum.ZERO,
                        rdPickUpPoint = (deliveryMethod as? Delivery.PickUpPointDelivery)?.currentPoint?.pickupPointCode,
                    ), accessToken = token
                )
            }
    }

    class Test : CreateDeliveryOrderUseCase {

        override suspend fun invoke(comment: String, deliveryMethod: Delivery): Result<Unit> =
            Result.success(Unit)
    }
}