package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidapi.api.address.AddressRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.processes.mapper.AddressesMapper
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.throwOnFailure

interface SaveAddressUseCase {

    suspend operator fun invoke(address: AddressUI): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val addressRepository: AddressRepository,
        private val addressesMapper: AddressesMapper
    ) : SaveAddressUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(address: AddressUI): Result<Unit> = withToken { token ->
            addressRepository.uploadClientAddress(
                token, addressesMapper.convertAddressUiModelToDto(address)
            ).throwOnFailure()
            UserData.address = address
        }
    }
}
