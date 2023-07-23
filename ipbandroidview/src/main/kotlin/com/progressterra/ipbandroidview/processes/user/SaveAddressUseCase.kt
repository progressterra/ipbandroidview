package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidapi.api.address.AddressRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData

interface SaveAddressUseCase {

    suspend operator fun invoke(address: AddressUI): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val addressRepository: AddressRepository
    ) : SaveAddressUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(address: AddressUI): Result<Unit> = withToken { token ->
            if (address != UserData.shippingAddress) {
                addressRepository.uploadClientAddress(
                    accessToken = token,
                    request = address.convertAddressUiModelToDto()
                )
                UserData.shippingAddress = address
            }
        }
    }
}
