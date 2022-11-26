package com.progressterra.ipbandroidview.domain.usecase.delivery

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.iecommerce.model.ParamForAddAddress
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.model.DeliveryMethod

interface SetDeliveryAddressUseCase {

    suspend fun setAddress(deliveryMethod: DeliveryMethod, address: String): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: CartRepository
    ) : SetDeliveryAddressUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun setAddress(
            deliveryMethod: DeliveryMethod,
            address: String
        ): Result<Unit> = withToken { token ->
            repo.setDeliveryAddress(
                token, ParamForAddAddress(
                    accessToken = token,
                    idAddress = deliveryMethod.type.value,
                    adressString = address
                )
            )
        }
    }
}