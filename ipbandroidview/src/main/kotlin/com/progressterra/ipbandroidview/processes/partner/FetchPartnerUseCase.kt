package com.progressterra.ipbandroidview.processes.partner

import com.progressterra.ipbandroidapi.api.collaboration.CollaborationRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.ProvideLocation
import com.progressterra.ipbandroidview.processes.AppSettings.HASSP_PARTNER_ID
import com.progressterra.ipbandroidview.entities.Partner

interface FetchPartnerUseCase {

    suspend operator fun invoke(): Result<Partner>

    class Base(
        private val repository: CollaborationRepository,
        private val partnerMapper: PartnerMapper,
        private val offerMapper: OfferMapper,
        private val provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository
    ) : FetchPartnerUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<Partner> = withToken { token ->
            val location = provideLocation.location().getOrNull()
            val resultOrganization = repository.organizationById(
                accessToken = token,
                latitude = location?.latitude?.toString() ?: "0.0",
                longitude = location?.longitude?.toString() ?: "0.0",
                organizationId = HASSP_PARTNER_ID
            ).getOrThrow()
            val resultShop = repository.organizationShops(
                accessToken = token,
                latitude = location?.latitude?.toString() ?: "0.0",
                longitude = location?.longitude?.toString() ?: "0.0",
                organizationId = HASSP_PARTNER_ID
            ).getOrThrow()
            val resultOffers = repository.offersByOrganization(
                accessToken = token,
                latitude = location?.latitude?.toString() ?: "0.0",
                longitude = location?.longitude?.toString() ?: "0.0",
                organizationId = HASSP_PARTNER_ID
            ).getOrThrow()?.map { offerMapper.map(it) } ?: emptyList()
            partnerMapper.map(resultOrganization!!, resultShop!!.first(), resultOffers)
        }
    }
}