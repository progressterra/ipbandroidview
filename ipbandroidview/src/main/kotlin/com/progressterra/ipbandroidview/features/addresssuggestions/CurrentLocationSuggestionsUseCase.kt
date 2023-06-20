package com.progressterra.ipbandroidview.features.addresssuggestions

import com.progressterra.ipbandroidapi.api.suggestion.SuggestionRepository
import com.progressterra.ipbandroidapi.api.suggestion.model.DadataSuggestionsFromLocationRequest
import com.progressterra.ipbandroidview.entities.convertSuggestionsDtoToUIModels
import com.progressterra.ipbandroidview.processes.location.ProvideLocation

interface CurrentLocationSuggestionsUseCase {

    suspend operator fun invoke(): Result<List<SuggestionUI>>

    class Base(
        private val provideLocation: ProvideLocation,
        private val repo: SuggestionRepository
    ) : CurrentLocationSuggestionsUseCase {

        override suspend fun invoke(): Result<List<SuggestionUI>> = runCatching {
            val locationResult = provideLocation.location().getOrThrow()
            repo.getSuggestionsAddressFromLocation(
                DadataSuggestionsFromLocationRequest(
                    latitude = locationResult.latitude.toFloat(),
                    longitude = locationResult.longitude.toFloat(),
                    count = 3
                )
            ).getOrThrow()?.map { it.convertSuggestionsDtoToUIModels() } ?: emptyList()
        }
    }
}