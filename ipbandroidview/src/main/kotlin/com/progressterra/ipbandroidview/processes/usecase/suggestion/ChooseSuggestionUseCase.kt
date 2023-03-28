package com.progressterra.ipbandroidview.processes.usecase.suggestion

import com.progressterra.ipbandroidview.processes.mapper.AddressesMapper
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.entities.SuggestionUI

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