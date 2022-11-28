package com.progressterra.ipbandroidview.domain.usecase.delivery

import android.util.Log
import com.progressterra.ipbandroidapi.api.ipbdelivery.IPBDeliveryRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.mapper.DeliveryMethodMapper
import com.progressterra.ipbandroidview.model.DeliveryMethod


interface AvailableDeliveryUseCase {

    suspend fun deliveries(): Result<List<DeliveryMethod>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val deliveryRepository: IPBDeliveryRepository,
        private val mapper: DeliveryMethodMapper
    ) : AvailableDeliveryUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun deliveries(): Result<List<DeliveryMethod>> = withToken { token ->
            deliveryRepository.getDeliveryList(token).onFailure {
                Log.e("Delivery list", "deliveries", it)
            }
            deliveryRepository.getDeliveryList(token).getOrThrow()?.map {
                mapper.map(it)
            } ?: emptyList()
        }
    }
}