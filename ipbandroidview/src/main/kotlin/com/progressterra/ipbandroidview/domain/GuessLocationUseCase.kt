package com.progressterra.ipbandroidview.domain

import com.google.android.gms.maps.model.LatLng
import com.progressterra.ipbandroidapi.api.suggestion.SuggestionRepository
import com.progressterra.ipbandroidview.domain.mapper.AddressGuesserMapper

interface GuessLocationUseCase {

    suspend fun guessLocation(latLng: LatLng): Result<String>

    class Base(
        private val repo: SuggestionRepository,
        private val mapper: AddressGuesserMapper
    ) : GuessLocationUseCase {

        override suspend fun guessLocation(latLng: LatLng): Result<String> = runCatching {
            val suggestionsResult = repo.getSuggestionsAddressFromLocation(
                latLng.latitude.toFloat(), latLng.longitude.toFloat(), 3
            ).getOrThrow()
            mapper.map(suggestionsResult.first())
        }
    }
}