package com.progressterra.ipbandroidview.processes.usecase.suggestion

import com.progressterra.ipbandroidapi.api.suggestion.SuggestionRepository
import com.progressterra.ipbandroidapi.api.suggestion.model.DadataSuggestionsFromLocationRequest
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.processes.mapper.AddressesMapper
import com.progressterra.ipbandroidview.entities.SuggestionUI

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