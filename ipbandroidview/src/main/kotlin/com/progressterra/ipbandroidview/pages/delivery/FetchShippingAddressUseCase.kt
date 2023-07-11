package com.progressterra.ipbandroidview.pages.delivery

import com.progressterra.ipbandroidapi.api.address.AddressRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.entities.toAddressUiModel
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData

interface FetchShippingAddressUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val addressRepository: AddressRepository
    ) : FetchShippingAddressUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<Unit> = withToken { token ->
            val address =
                addressRepository.defaultShippingAddress(token).getOrThrow()?.toAddressUiModel()
                    ?: AddressUI()
            UserData.shippingAddress = address
        }
    }
}