package com.progressterra.ipbandroidview.widgets.deliverypicker

import com.progressterra.ipbandroidapi.api.address.AddressRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.entities.convertAddressUiModelToDto
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface SetDeliveryAddressUseCase {

    suspend operator fun invoke(deliveryMethod: Delivery, address: AddressUI): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: AddressRepository
    ) : SetDeliveryAddressUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(
            deliveryMethod: Delivery,
            address: AddressUI
        ): Result<Unit> = withToken { token ->
            repo.updateClientAddress(
                accessToken = token,
                request = address.convertAddressUiModelToDto()
            )
        }
    }
}