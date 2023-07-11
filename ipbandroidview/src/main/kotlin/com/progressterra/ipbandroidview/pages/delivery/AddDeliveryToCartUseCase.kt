package com.progressterra.ipbandroidview.pages.delivery

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.cart.models.IncomdeDataAddress
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData

interface AddDeliveryToCartUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val cartRepository: CartRepository
    ) : AbstractTokenUseCase(scrmRepository, provideLocation), AddDeliveryToCartUseCase {

        override suspend fun invoke(): Result<Unit> = withToken {
            cartRepository.addAddressToCart(
                accessToken = it,
                income = IncomdeDataAddress(
                    addressString = UserData.shippingAddress.printAddress(),
                    idAddress = UserData.shippingAddress.idUnique
                )
            )
        }
    }
}