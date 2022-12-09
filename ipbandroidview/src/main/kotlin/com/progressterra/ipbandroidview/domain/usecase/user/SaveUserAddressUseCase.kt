package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidapi.api.address.AddressRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.data.UserData
import com.progressterra.ipbandroidview.domain.mapper.AddressesMapper
import com.progressterra.ipbandroidview.ext.throwOnFailure
import com.progressterra.ipbandroidview.model.address.AddressUI

interface SaveUserAddressUseCase {

    suspend operator fun invoke(address: AddressUI): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val addressRepository: AddressRepository,
        private val addressesMapper: AddressesMapper
    ) : SaveUserAddressUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(address: AddressUI): Result<Unit> = withToken { token ->
            addressRepository.uploadClientAddress(
                token,
                addressesMapper.convertAddressUiModelToDto(address)
            ).throwOnFailure()
            UserData.address = address
        }
    }
}