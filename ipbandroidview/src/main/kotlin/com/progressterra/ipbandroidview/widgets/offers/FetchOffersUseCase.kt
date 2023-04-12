package com.progressterra.ipbandroidview.widgets.offers

import com.progressterra.ipbandroidapi.api.collaboration.CollaborationRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.features.offer.OfferState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractUseCase

interface FetchOffersUseCase {

    suspend operator fun invoke(): Result<OffersState>

    class Base(
        private val repository: CollaborationRepository,
        private val offerMapper: OfferMapper,
        private val provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository
    ) : FetchOffersUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<OffersState> = withToken { token ->
            val location = provideLocation.location().getOrNull()
            OffersState(
                buildList {
                    repository.partners(
                        accessToken = token,
                        latitude = location?.latitude?.toString() ?: "0.0",
                        longitude = location?.longitude?.toString() ?: "0.0"
                    ).getOrThrow()?.forEach {
                        repository.offersByOrganization(
                            accessToken = token,
                            latitude = location?.latitude?.toString() ?: "0.0",
                            longitude = location?.longitude?.toString() ?: "0.0",
                            organizationId = it.idUnique!!
                        ).getOrThrow()?.forEach { offer -> add(offerMapper.map(offer)) }
                    }
                }
            )
        }
    }

    class Test : FetchOffersUseCase {

        override suspend fun invoke(): Result<OffersState> = Result.success(
            OffersState(
                listOf(
                    OfferState(
                        title = "Акция 1",
                        image = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                    ),
                    OfferState(
                        title = "Акция 2",
                        image = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                    ),
                    OfferState(
                        title = "Акция 3",
                        image = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                    )
                )
            )
        )
    }
}