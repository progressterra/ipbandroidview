package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.suggestion.SuggestionRepository
import com.progressterra.ipbandroidview.base.AbstractUseCase
import com.progressterra.ipbandroidview.base.UseCaseException
import com.progressterra.ipbandroidview.data.ProvideLocation
import com.progressterra.ipbandroidview.domain.mapper.SuggestionMapper
import com.progressterra.ipbandroidview.ui.city.Suggestion

interface CurrentLocationUseCase {

    suspend fun currentLocation(): Result<List<Suggestion>>

    class Base(
        private val provideLocation: ProvideLocation,
        private val repo: SuggestionRepository,
        private val mapper: SuggestionMapper
    ) : CurrentLocationUseCase, AbstractUseCase() {

        override suspend fun currentLocation(): Result<List<Suggestion>> = handle {
            val location = provideLocation.location()
            val suggestionsResult = repo.getSuggestionsAddressFromLocation(
                location.latitude.toFloat(), location.longitude.toFloat(), 3
            )
            if (suggestionsResult.isFailure) throw UseCaseException()
            suggestionsResult.getOrNull()!!.map { mapper.map(it) }
        }
    }
}