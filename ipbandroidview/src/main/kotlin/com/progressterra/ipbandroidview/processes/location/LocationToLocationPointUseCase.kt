package com.progressterra.ipbandroidview.processes.location

import android.location.Location
import com.progressterra.ipbandroidview.entities.LocationPoint
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources

interface LocationToLocationPointUseCase {

    suspend operator fun invoke(location: Location): Result<LocationPoint>

    class Base(makeToastUseCase: MakeToastUseCase, manageResources: ManageResources) : LocationToLocationPointUseCase, AbstractLoggingUseCase(
        makeToastUseCase, manageResources
    ) {

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