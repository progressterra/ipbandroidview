package com.progressterra.ipbandroidview.widgets.deliverypicker

import com.progressterra.ipbandroidapi.api.ipbdelivery.IPBDeliveryRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.Delivery
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.UserData


interface FetchAvailableDeliveryUseCase {

    suspend operator fun invoke(): Result<DeliveryPickerState>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val deliveryRepository: IPBDeliveryRepository,
        private val mapper: DeliveryMethodMapper
    ) : FetchAvailableDeliveryUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<DeliveryPickerState> = withToken { token ->
            val deliveryList =
                deliveryRepository.getDeliveryList(token).getOrThrow()?.map { mapper.map(it) }
                    ?: emptyList()
            DeliveryPickerState(
                addressUI = UserData.address,
                selectedDeliveryMethod = deliveryList.firstOrNull(),
                deliveryMethods = deliveryList
            )
        }
    }

    class Test : FetchAvailableDeliveryUseCase {

        override suspend fun invoke(): Result<DeliveryPickerState> = runCatching {
            val deliveries = listOf(Delivery.CourierDelivery(price = SimplePrice(500), type = ""))
            DeliveryPickerState(
                addressUI = UserData.address,
                deliveryMethods = deliveries
            )
        }
    }
}