package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidapi.api.address.AddressRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.data.UserData
import com.progressterra.ipbandroidview.domain.mapper.AddressesMapper
import com.progressterra.ipbandroidview.model.AddressUI

interface SaveUserAddressUseCase {

    suspend fun saveAddress(address: AddressUI): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val addressRepository: AddressRepository,
        private val addressesMapper: AddressesMapper
    ) : SaveUserAddressUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun saveAddress(address: AddressUI): Result<Unit> = withToken { token ->
            addressRepository.uploadClientAddress(
                token,
                addressesMapper.convertAddressUiModelToDto(address)
            ).onFailure { throw it }
            UserData.address = address
        }
    }
}