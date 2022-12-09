package com.progressterra.ipbandroidview.domain.usecase

import com.progressterra.ipbandroidview.domain.mapper.AddressesMapper
import com.progressterra.ipbandroidview.model.address.AddressUI
import com.progressterra.ipbandroidview.model.address.SuggestionUI

interface ChooseSuggestionUseCase {

    suspend operator fun invoke(suggestionUI: SuggestionUI): Result<AddressUI>

    class Base(
        private val addressesMapper: AddressesMapper
    ) : ChooseSuggestionUseCase {

        override suspend fun invoke(suggestionUI: SuggestionUI): Result<AddressUI> = runCatching {
            addressesMapper.convertSuggestionToAddressUIModel(suggestionUI.suggestionExtendedInfo)
        }
    }
}