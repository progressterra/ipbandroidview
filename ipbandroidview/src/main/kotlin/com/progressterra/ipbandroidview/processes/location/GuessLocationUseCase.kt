package com.progressterra.ipbandroidview.processes.location

import com.google.android.gms.maps.model.LatLng
import com.progressterra.ipbandroidapi.api.suggestion.SuggestionRepository
import com.progressterra.ipbandroidapi.api.suggestion.model.DadataSuggestionsFromLocationRequest
import com.progressterra.ipbandroidview.entities.Address
import com.progressterra.ipbandroidview.entities.convertSuggestionToAddressUIModel
import com.progressterra.ipbandroidview.entities.formatZdtIso
import java.time.ZonedDateTime

interface GuessLocationUseCase {

    suspend operator fun invoke(latLng: LatLng): Result<Address>

    class Base(
        private val repo: SuggestionRepository,
    ) : GuessLocationUseCase {

        override suspend fun invoke(latLng: LatLng): Result<Address> = runCatching {
            val suggestionsResult = repo.getSuggestionsAddressFromLocation(
                DadataSuggestionsFromLocationRequest(
                    latitude = latLng.latitude.toFloat(),
                    longitude = latLng.longitude.toFloat(),
                    count = 3
                )
            ).getOrThrow()
            suggestionsResult?.firstOrNull()?.suggestionExtendedInfo?.convertSuggestionToAddressUIModel(
                ZonedDateTime.now().formatZdtIso()
            ) ?: Address()
        }
    }
}