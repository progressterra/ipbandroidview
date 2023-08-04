package com.progressterra.ipbandroidview.processes.location

import com.google.android.gms.maps.model.LatLng
import com.progressterra.ipbandroidapi.api.suggestion.SuggestionRepository
import com.progressterra.ipbandroidapi.api.suggestion.model.DadataSuggestionsFromLocationRequest
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.entities.convertSuggestionToAddressUIModel
import com.progressterra.ipbandroidview.entities.formatZDT
import java.time.ZonedDateTime

interface GuessLocationUseCase {

    suspend operator fun invoke(latLng: LatLng): Result<AddressUI>

    class Base(
        private val repo: SuggestionRepository,
    ) : GuessLocationUseCase {

        override suspend fun invoke(latLng: LatLng): Result<AddressUI> = runCatching {
            val suggestionsResult = repo.getSuggestionsAddressFromLocation(
                DadataSuggestionsFromLocationRequest(
                    latitude = latLng.latitude.toFloat(),
                    longitude = latLng.longitude.toFloat(),
                    count = 3
                )
            ).getOrThrow()
            suggestionsResult?.firstOrNull()?.suggestionExtendedInfo?.convertSuggestionToAddressUIModel(
                ZonedDateTime.now().formatZDT()
            ) ?: AddressUI()
        }
    }
}