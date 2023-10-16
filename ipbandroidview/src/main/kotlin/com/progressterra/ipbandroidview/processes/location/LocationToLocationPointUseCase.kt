package com.progressterra.ipbandroidview.processes.location

import android.location.Location
import com.progressterra.ipbandroidview.entities.LocationPoint
import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase

interface LocationToLocationPointUseCase {

    suspend operator fun invoke(location: Location): Result<LocationPoint>

    class Base : LocationToLocationPointUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(location: Location): Result<LocationPoint> = handle {
            LocationPoint(
                id = "",
                name = "",
                latitude = location.latitude,
                longitude = location.longitude,
                address = ""
            )
        }
    }
}