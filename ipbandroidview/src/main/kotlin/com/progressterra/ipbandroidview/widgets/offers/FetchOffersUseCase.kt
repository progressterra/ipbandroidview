package com.progressterra.ipbandroidview.widgets.offers

import com.progressterra.ipbandroidapi.api.collaboration.CollaborationRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface FetchOffersUseCase {

    suspend operator fun invoke(): Result<OffersState>

    class Base(
        private val repository: CollaborationRepository,
        private val offerMapper: OfferMapper,
        private val provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository
    ) : FetchOffersUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

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
}