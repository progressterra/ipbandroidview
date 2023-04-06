package com.progressterra.ipbandroidview.widgets.deliverypicker

import com.progressterra.ipbandroidapi.api.ipbdelivery.IPBDeliveryRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.ProvideLocation
import com.progressterra.ipbandroidview.processes.usecase.user.FetchUserAddressUseCase


interface FetchAvailableDeliveryUseCase {

    suspend operator fun invoke(): Result<DeliveryPickerState>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val deliveryRepository: IPBDeliveryRepository,
        private val fetchUserAddressUseCase: FetchUserAddressUseCase,
        private val mapper: DeliveryMethodMapper
    ) : FetchAvailableDeliveryUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<DeliveryPickerState> = withToken { token ->
            val deliveryList =
                deliveryRepository.getDeliveryList(token).getOrThrow()?.map { mapper.map(it) }
                    ?: emptyList()
            DeliveryPickerState(
                addressUI = fetchUserAddressUseCase().getOrThrow(),
                selectedDeliveryMethod = deliveryList.firstOrNull(),
                deliveryMethods = deliveryList
            )
        }
    }
}