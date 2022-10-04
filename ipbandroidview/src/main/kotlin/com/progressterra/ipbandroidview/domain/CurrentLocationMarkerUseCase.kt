package com.progressterra.ipbandroidview.domain

import com.google.android.gms.maps.model.LatLng
import com.progressterra.ipbandroidapi.api.suggestion.SuggestionRepository
import com.progressterra.ipbandroidview.base.AbstractUseCase
import com.progressterra.ipbandroidview.base.UseCaseException
import com.progressterra.ipbandroidview.data.ProvideLocation
import com.progressterra.ipbandroidview.domain.mapper.SuggestionMapper
import com.progressterra.ipbandroidview.ui.city.MapMarker
import com.progressterra.ipbandroidview.ui.city.Suggestion

interface CurrentLocationMarkerUseCase {

    suspend fun currentLocation(): Result<MapMarker>

    class Base(
        private val provideLocation: ProvideLocation
    ) : CurrentLocationMarkerUseCase, AbstractUseCase() {

        override suspend fun currentLocation(): Result<MapMarker> = handle {
            val location = provideLocation.location()
            MapMarker(LatLng(location.latitude, location.longitude))
        }
    }
}