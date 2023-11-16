package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.GeoData
import com.progressterra.ipbandroidapi.api.iamhere.models.GeoPoint
import com.progressterra.ipbandroidapi.api.iamhere.models.IncomeDataStartMeet
import com.progressterra.ipbandroidapi.api.iamhere.models.RFTargetViewModel
import com.progressterra.ipbandroidview.entities.DatingTarget
import com.progressterra.ipbandroidview.entities.formatZdtIso
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.location.LocationToLocationPointUseCase
import com.progressterra.ipbandroidview.processes.location.ProvideLocationUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import java.time.ZonedDateTime

interface ReadyToMeetUseCase {

    suspend operator fun invoke(target: DatingTarget): Result<Unit>

    class Base(
        private val locationToLocationPointUseCase: LocationToLocationPointUseCase,
        private val imhService: ImhService,
        private val provideLocationUseCase: ProvideLocationUseCase,
        makeToastUseCase: MakeToastUseCase,
        obtainAccessToken: ObtainAccessToken,

        manageResources: ManageResources,
    ) : ReadyToMeetUseCase, AbstractTokenUseCase(
        obtainAccessToken, makeToastUseCase, manageResources
    ) {

        override suspend fun invoke(
            target: DatingTarget
        ): Result<Unit> =
            withToken { token ->
                val location = provideLocationUseCase().getOrThrow()
                val locationPoint = locationToLocationPointUseCase(location).getOrThrow()
                imhService.clientDataReadyMeet(
                    token = token, body = IncomeDataStartMeet(
                        geoDataEnity = GeoData(
                            namePlace = locationPoint.name,
                            addressPlace = locationPoint.address,
                            idrfPlace = null,
                            geoPointPlace = GeoPoint(
                                latitude = location.latitude, longitude = location.longitude
                            ),
                            geoPointReal = GeoPoint(
                                latitude = location.latitude, longitude = location.longitude
                            )
                        ), target = RFTargetViewModel(
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