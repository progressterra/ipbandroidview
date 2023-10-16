package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.GeoData
import com.progressterra.ipbandroidapi.api.iamhere.models.GeoPoint
import com.progressterra.ipbandroidview.entities.LocationPoint
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface UpdateDatingLocationUseCase {

    suspend operator fun invoke(locationPoint: LocationPoint)

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val imhService: ImhService
    ) : UpdateDatingLocationUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(locationPoint: LocationPoint) {
            withToken { token ->
                imhService.clientDataGeoData(
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
            }
        }
    }
}