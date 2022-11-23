package com.progressterra.ipbandroidview.domain.usecase.delivery

import com.progressterra.ipbandroidapi.api.ipbdelivery.IPBDeliveryRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.mapper.DeliveryMethodMapper
import com.progressterra.ipbandroidview.model.DeliveryMethod

interface AvailableDeliveryMethodsUseCase {

    suspend fun availableDeliveryMethods(): Result<List<DeliveryMethod>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val deliveryRepository: IPBDeliveryRepository,
        private val deliveryMethodMapper: DeliveryMethodMapper
    ) : AvailableDeliveryMethodsUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun availableDeliveryMethods(): Result<List<DeliveryMethod>> =
            withToken { token ->
                deliveryRepository.getDeliveryList(token).getOrThrow()!!.map {
                    deliveryMethodMapper.map(it)
                }
            }
    }
}