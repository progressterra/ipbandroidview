package com.progressterra.ipbandroidview.pages.delivery

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.cart.models.IncomdeDataAddress
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.entities.convertSuggestionToAddressUIModel
import com.progressterra.ipbandroidview.features.addresssuggestions.SuggestionUI
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.processes.user.SaveAddressUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.throwOnFailure
import java.util.Date

interface AddDeliveryToCartUseCase {

    suspend operator fun invoke(address: AddressUI?, suggestionUI: SuggestionUI?): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val cartRepository: CartRepository,
        private val saveAddressUseCase: SaveAddressUseCase
    ) : AbstractTokenUseCase(scrmRepository, provideLocation), AddDeliveryToCartUseCase {

        override suspend fun invoke(
            address: AddressUI?, suggestionUI: SuggestionUI?
        ): Result<Unit> = withToken {
            if (address == null) {
                saveAddressUseCase(
                    suggestionUI?.suggestionExtendedInfo?.convertSuggestionToAddressUIModel(
                        Date().format()
                    )!!
                ).throwOnFailure()
            }
            cartRepository.addAddressToCart(
                accessToken = it, income = IncomdeDataAddress(
                    addressString = if (address == null) UserData.shippingAddress.printAddress() else suggestionUI?.previewOfSuggestion
                        ?: "", idAddress = UserData.shippingAddress.idUnique
                )
            )
        }
    }
}