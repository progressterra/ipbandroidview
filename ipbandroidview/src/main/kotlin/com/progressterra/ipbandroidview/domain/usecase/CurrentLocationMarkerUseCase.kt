package com.progressterra.ipbandroidview.domain.usecase

import com.google.android.gms.maps.model.LatLng
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.ui.city.MapMarker

interface CurrentLocationMarkerUseCase {

    suspend fun currentLocation(): Result<MapMarker>

    class Base(
        private val provideLocation: ProvideLocation
    ) : CurrentLocationMarkerUseCase {

        override suspend fun currentLocation(): Result<MapMarker> = runCatching {
            val locationResult = provideLocation.location().getOrThrow()
            MapMarker(
                LatLng(
                    locationResult.latitude,
                    locationResult.longitude
                )
            )
        }
    }
}