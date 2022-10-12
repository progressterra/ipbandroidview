package com.progressterra.ipbandroidview.domain

import com.google.android.gms.maps.model.LatLng
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.data.ProvideLocation
import com.progressterra.ipbandroidview.ui.city.MapMarker

interface CurrentLocationMarkerUseCase {

    suspend fun currentLocation(): Result<MapMarker>

    class Base(
        private val provideLocation: ProvideLocation
    ) : CurrentLocationMarkerUseCase, AbstractUseCase() {

        override suspend fun currentLocation(): Result<MapMarker> = handle {
            val locationResult = provideLocation.location()
            if (locationResult.isFailure) throw locationResult.exceptionOrNull()!!
            MapMarker(
                LatLng(
                    locationResult.getOrNull()!!.latitude,
                    locationResult.getOrNull()!!.longitude
                )
            )
        }
    }
}