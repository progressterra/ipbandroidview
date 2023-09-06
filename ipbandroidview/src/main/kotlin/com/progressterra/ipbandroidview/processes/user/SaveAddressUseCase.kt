package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidapi.api.scrm.ScrmService
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData

interface SaveAddressUseCase {

    suspend operator fun invoke(address: AddressUI): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val scrmService: ScrmService
    ) : SaveAddressUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(address: AddressUI): Result<Unit> = withToken { token ->
            if (address != UserData.shippingAddress) {
                scrmService.postAddress(
                    token = token,
                    body = address.convertAddressUiModelToDto()
                )
                UserData.shippingAddress = address
            }
        }
    }
}
