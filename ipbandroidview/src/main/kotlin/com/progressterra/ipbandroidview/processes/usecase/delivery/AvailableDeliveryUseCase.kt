package com.progressterra.ipbandroidview.processes.usecase.delivery

import com.progressterra.ipbandroidapi.api.ipbdelivery.IPBDeliveryRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.processes.mapper.DeliveryMethodMapper
import com.progressterra.ipbandroidview.entities.Delivery


interface AvailableDeliveryUseCase {

    suspend operator fun invoke(): Result<Map<DeliveryType, Delivery>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val deliveryRepository: IPBDeliveryRepository,
        private val mapper: DeliveryMethodMapper
    ) : AvailableDeliveryUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<Map<DeliveryType, Delivery>> = withToken { token ->
            mapper.map(deliveryRepository.getDeliveryList(token).getOrThrow() ?: emptyList())
        }
    }
}