package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.suggestion.SuggestionRepository
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.mapper.SuggestionMapper
import com.progressterra.ipbandroidview.ui.city.Suggestion

interface CurrentLocationSuggestionsUseCase {

    suspend fun currentLocation(): Result<List<Suggestion>>

    class Base(
        private val provideLocation: ProvideLocation,
        private val repo: SuggestionRepository,
        private val mapper: SuggestionMapper
    ) : CurrentLocationSuggestionsUseCase {

        override suspend fun currentLocation(): Result<List<Suggestion>> = runCatching {
            val locationResult = provideLocation.location().getOrThrow()
            val suggestionsResult = repo.getSuggestionsAddressFromLocation(
                locationResult.latitude.toFloat(),
                locationResult.longitude.toFloat(),
                3
            ).getOrThrow()
            suggestionsResult.map { mapper.map(it) }
        }
    }
}