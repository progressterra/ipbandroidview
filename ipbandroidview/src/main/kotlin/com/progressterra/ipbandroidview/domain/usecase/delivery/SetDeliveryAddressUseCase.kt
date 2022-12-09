package com.progressterra.ipbandroidview.domain.usecase.delivery

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.iecommerce.model.ParamForAddAddress
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.model.address.AddressUI
import com.progressterra.ipbandroidview.model.delivery.Delivery

interface SetDeliveryAddressUseCase {

    suspend operator fun invoke(deliveryMethod: Delivery, address: AddressUI): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: CartRepository
    ) : SetDeliveryAddressUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(
            deliveryMethod: Delivery,
            address: AddressUI
        ): Result<Unit> = withToken { token ->
            repo.setDeliveryAddress(
                token, ParamForAddAddress(
                    accessToken = token,
                    idAddress = deliveryMethod.id.value,
                    adressString = address.toString()
                )
            )
        }
    }
}