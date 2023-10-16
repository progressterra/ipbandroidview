package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.GeoData
import com.progressterra.ipbandroidapi.api.iamhere.models.GeoPoint
import com.progressterra.ipbandroidapi.api.iamhere.models.IncomeDataStartMeet
import com.progressterra.ipbandroidapi.api.iamhere.models.RFTargetViewModel
import com.progressterra.ipbandroidview.entities.DatingTarget
import com.progressterra.ipbandroidview.entities.LocationPoint
import com.progressterra.ipbandroidview.entities.formatZdtIso
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import java.time.ZonedDateTime

interface ReadyToMeetUseCase {

    suspend operator fun invoke(location: LocationPoint, target: DatingTarget): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val imhService: ImhService
    ) : ReadyToMeetUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(location: LocationPoint, target: DatingTarget): Result<Unit> =
            withToken { token ->
                imhService.clientDataReadyMeet(
                    token = token,
                    body = IncomeDataStartMeet(
                        geoDataEnity = GeoData(
                            namePlace = location.name,
                            addressPlace = location.address,
                            idrfPlace = null,
                            geoPointPlace = GeoPoint(
                                latitude = location.latitude,
                                longitude = location.longitude
                            )
                        ),
                        target = RFTargetViewModel(
                            name = target.name,
                            idUnique = target.id,
                            idEnterprise = null,
                            dateAdded = ZonedDateTime.now().formatZdtIso(),
                            dateUpdated = ZonedDateTime.now().formatZdtIso(),
                            dateSoftRemoved = null,
                            listImages = emptyList()
                        )
                    )
                )
            }
    }
}