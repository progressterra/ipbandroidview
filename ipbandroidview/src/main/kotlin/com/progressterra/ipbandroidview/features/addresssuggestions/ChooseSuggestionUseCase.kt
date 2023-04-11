package com.progressterra.ipbandroidview.features.addresssuggestions

import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.processes.mapper.AddressesMapper

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