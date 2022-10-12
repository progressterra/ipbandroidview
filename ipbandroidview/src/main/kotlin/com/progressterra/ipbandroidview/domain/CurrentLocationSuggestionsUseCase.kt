package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.suggestion.SuggestionRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.UseCaseException
import com.progressterra.ipbandroidview.data.ProvideLocation
import com.progressterra.ipbandroidview.domain.mapper.SuggestionMapper
import com.progressterra.ipbandroidview.ui.city.Suggestion

interface CurrentLocationSuggestionsUseCase {

    suspend fun currentLocation(): Result<List<Suggestion>>

    class Base(
        private val provideLocation: ProvideLocation,
        private val repo: SuggestionRepository,
        private val mapper: SuggestionMapper
    ) : CurrentLocationSuggestionsUseCase, AbstractUseCase() {

        override suspend fun currentLocation(): Result<List<Suggestion>> = handle {
            val locationResult = provideLocation.location()
            if (locationResult.isFailure) emptyList<Suggestion>()
            val suggestionsResult = repo.getSuggestionsAddressFromLocation(
                locationResult.getOrNull()!!.latitude.toFloat(),
                locationResult.getOrNull()!!.longitude.toFloat(),
                3
            )
            if (suggestionsResult.isFailure) throw UseCaseException()
            suggestionsResult.getOrNull()!!.map { mapper.map(it) }
        }
    }
}