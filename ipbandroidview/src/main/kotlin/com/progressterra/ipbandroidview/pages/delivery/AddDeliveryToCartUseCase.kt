package com.progressterra.ipbandroidview.pages.delivery

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.cart.models.IncomdeDataAddress
import com.progressterra.ipbandroidview.entities.convertSuggestionToAddressUIModel
import com.progressterra.ipbandroidview.entities.formatZDT
import com.progressterra.ipbandroidview.features.addresssuggestions.SuggestionUI
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.user.SaveAddressUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.throwOnFailure
import java.time.ZonedDateTime

interface AddDeliveryToCartUseCase {

    suspend operator fun invoke(suggestionUI: SuggestionUI?): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val cartRepository: CartRepository,
        private val saveAddressUseCase: SaveAddressUseCase
    ) : AbstractTokenUseCase(obtainAccessToken), AddDeliveryToCartUseCase {

        override suspend fun invoke(suggestionUI: SuggestionUI?): Result<Unit> =
            withToken { token ->
                suggestionUI?.let {
                    saveAddressUseCase(
                        it.suggestionExtendedInfo.convertSuggestionToAddressUIModel(
                            ZonedDateTime.now().formatZDT()
                        )
                    ).throwOnFailure()
                    cartRepository.addAddressToCart(
                        accessToken = token, income = IncomdeDataAddress(
                            addressString = suggestionUI.previewOfSuggestion,
                            idAddress = UserData.shippingAddress.idUnique
                        )
                    )
                }
                cartRepository.addAddressToCart(
                    accessToken = token, income = IncomdeDataAddress(
                        addressString = UserData.shippingAddress.printAddress(),
                        idAddress = UserData.shippingAddress.idUnique
                    )
                )
            }
    }
}