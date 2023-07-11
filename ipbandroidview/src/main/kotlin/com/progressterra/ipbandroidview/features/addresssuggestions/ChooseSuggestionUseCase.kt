package com.progressterra.ipbandroidview.features.addresssuggestions

import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.entities.convertSuggestionToAddressUIModel
import java.util.Date

interface ChooseSuggestionUseCase {

    suspend operator fun invoke(suggestionUI: SuggestionUI): Result<AddressUI>

    class Base(
    ) : ChooseSuggestionUseCase {

        override suspend fun invoke(suggestionUI: SuggestionUI): Result<AddressUI> = runCatching {
            suggestionUI.suggestionExtendedInfo.convertSuggestionToAddressUIModel(Date().format())
        }
    }
}