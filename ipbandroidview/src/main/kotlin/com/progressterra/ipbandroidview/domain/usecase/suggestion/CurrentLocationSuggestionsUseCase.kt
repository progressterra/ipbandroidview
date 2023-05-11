package com.progressterra.ipbandroidview.domain.usecase.suggestion

import com.progressterra.ipbandroidapi.api.suggestion.SuggestionRepository
import com.progressterra.ipbandroidapi.api.suggestion.model.DadataSuggestionsFromLocationRequest
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.mapper.AddressesMapper
import com.progressterra.ipbandroidview.model.SuggestionUI

interface CurrentLocationSuggestionsUseCase {

    suspend operator fun invoke(): Result<List<SuggestionUI>>

    class Base(
        private val provideLocation: ProvideLocation,
        private val repo: SuggestionRepository,
        private val mapper: AddressesMapper
    ) : CurrentLocationSuggestionsUseCase {

        override suspend fun invoke(): Result<List<SuggestionUI>> = runCatching {
            val locationResult = provideLocation.location().getOrThrow()
            mapper.convertSuggestionsDtoToUIModels(
                repo.getSuggestionsAddressFromLocation(
                    DadataSuggestionsFromLocationRequest(
                        latitude = locationResult.latitude.toFloat(),
                        longitude = locationResult.longitude.toFloat(),
                        count = 3
                    )
                ).getOrThrow()
            )
        }
    }
}