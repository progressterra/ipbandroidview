package com.progressterra.ipbandroidview.domain.usecase

import com.progressterra.ipbandroidapi.api.suggestion.SuggestionRepository
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.mapper.SuggestionMapper
import com.progressterra.ipbandroidview.model.Suggestion

interface CurrentLocationSuggestionsUseCase {

    suspend fun currentLocation(): Result<List<Suggestion>>

    class Base(
        private val provideLocation: ProvideLocation,
        private val repo: SuggestionRepository,
        private val mapper: SuggestionMapper
    ) : CurrentLocationSuggestionsUseCase {

        override suspend fun currentLocation(): Result<List<Suggestion>> = runCatching {
            val locationResult = provideLocation.location().getOrThrow()
            repo.getSuggestionsAddressFromLocation(
                locationResult.latitude.toFloat(),
                locationResult.longitude.toFloat(),
                3
            ).getOrThrow().map { mapper.map(it) }
        }
    }
}