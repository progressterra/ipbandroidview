package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.GeoData
import com.progressterra.ipbandroidapi.api.iamhere.models.GeoPoint
import com.progressterra.ipbandroidapi.api.iamhere.models.StatusResult
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.LocationPoint
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface UpdateDatingLocationUseCase {

    suspend operator fun invoke(locationPoint: LocationPoint): Result<Unit>

    class Base(
        private val imhService: ImhService,
        makeToastUseCase: MakeToastUseCase,
        obtainAccessToken: ObtainAccessToken,
        manageResources: ManageResources
    ) : UpdateDatingLocationUseCase, AbstractTokenUseCase(
        obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(locationPoint: LocationPoint): Result<Unit> =
            withToken { token ->
                val response = imhService.clientDataGeoData(
                    token = token,
                    body = GeoData(
                        namePlace = locationPoint.name,
                        addressPlace = locationPoint.address,
                        idrfPlace = null,
                        geoPointPlace = GeoPoint(
                            latitude = locationPoint.latitude,
                            longitude = locationPoint.longitude
                        )
                    )
                )
                if (response.result?.status != StatusResult.SUCCESS) {
                    throw ToastedException(R.string.location_update_error)
                }
            }
    }
}