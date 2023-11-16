package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.GeoData
import com.progressterra.ipbandroidapi.api.iamhere.models.GeoPoint
import com.progressterra.ipbandroidapi.api.iamhere.models.StatusResult
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.location.LocationToLocationPointUseCase
import com.progressterra.ipbandroidview.processes.location.ProvideLocationUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources

interface UpdateDatingLocationUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base(
        private val imhService: ImhService,
        private val locationToLocationPointUseCase: LocationToLocationPointUseCase,
        private val provideLocation: ProvideLocationUseCase,
        makeToastUseCase: MakeToastUseCase,
        obtainAccessToken: ObtainAccessToken,
        manageResources: ManageResources
    ) : UpdateDatingLocationUseCase, AbstractTokenUseCase(
        obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(): Result<Unit> =
            withToken { token ->
                val location = provideLocation().getOrThrow()
                val locationPoint = locationToLocationPointUseCase(location).getOrThrow()
                val response = imhService.clientDataGeoData(
                    token = token,
                    body = GeoData(
                        namePlace = locationPoint.name,
                        addressPlace = locationPoint.address,
                        idrfPlace = null,
                        geoPointPlace = GeoPoint(
                            latitude = locationPoint.latitude,
                            longitude = locationPoint.longitude
                        ),
                        geoPointReal = GeoPoint(
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