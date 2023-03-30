package com.progressterra.ipbandroidview.processes.partner

import com.progressterra.ipbandroidapi.api.collaboration.CollaborationRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.entities.Offer

interface FetchOffersUseCase {
    suspend operator fun invoke(): Result<List<Offer>>

    class Base(
        private val repository: CollaborationRepository,
        private val offerMapper: OfferMapper,
        private val provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository
    ) : FetchOffersUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<List<Offer>> = withToken { token ->
            val location = provideLocation.location().getOrNull()
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
        }
    }
}