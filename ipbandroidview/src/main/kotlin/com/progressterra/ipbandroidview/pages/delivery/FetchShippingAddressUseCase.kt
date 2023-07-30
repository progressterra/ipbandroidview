package com.progressterra.ipbandroidview.pages.delivery

import com.progressterra.ipbandroidapi.api.address.AddressRepository
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.entities.toAddressUiModel
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData

interface FetchShippingAddressUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val addressRepository: AddressRepository
    ) : FetchShippingAddressUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<Unit> = withToken { token ->
            val address =
                addressRepository.defaultShippingAddress(token).getOrThrow()?.toAddressUiModel()
                    ?: AddressUI()
            UserData.shippingAddress = address
        }
    }
}