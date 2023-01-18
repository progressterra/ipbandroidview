package com.progressterra.ipbandroidview.domain.usecase.location

import com.google.android.gms.maps.model.LatLng
import com.progressterra.ipbandroidapi.api.suggestion.SuggestionRepository
import com.progressterra.ipbandroidapi.api.suggestion.model.DadataSuggestionsFromLocationRequest
import com.progressterra.ipbandroidapi.api.suggestion.model.SuggestionExtendedInfo
import com.progressterra.ipbandroidview.domain.mapper.AddressesMapper
import com.progressterra.ipbandroidview.model.address.AddressUI

interface GuessLocationUseCase {

    suspend operator fun invoke(latLng: LatLng): Result<AddressUI>

    class Base(
        private val repo: SuggestionRepository,
        private val addressesMapper: AddressesMapper
    ) : GuessLocationUseCase {

        override suspend fun invoke(latLng: LatLng): Result<AddressUI> = runCatching {
            val suggestionsResult = repo.getSuggestionsAddressFromLocation(
                DadataSuggestionsFromLocationRequest(
                    latitude = latLng.latitude.toFloat(),
                    longitude = latLng.longitude.toFloat(),
                    count = 3
                )
            ).getOrThrow()
            addressesMapper.convertSuggestionToAddressUIModel(
                suggestionsResult?.first()?.suggestionExtendedInfo ?: SuggestionExtendedInfo()
            )
        }
    }
}