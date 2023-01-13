package com.progressterra.ipbandroidview.domain.usecase.partner

import com.progressterra.ipbandroidapi.api.collaboration.CollaborationRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.AppSettings.PARTNER_ID
import com.progressterra.ipbandroidview.domain.mapper.OfferMapper
import com.progressterra.ipbandroidview.domain.mapper.PartnerMapper
import com.progressterra.ipbandroidview.model.partner.Partner

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
            val location = provideLocation.location().getOrThrow()
            val resultOrganization = repository.organizationById(
                accessToken = token,
                latitude = location.latitude.toString(),
                longitude = location.longitude.toString(),
                organizationId = PARTNER_ID
            ).getOrThrow()
            val resultShop = repository.organizationShops(
                accessToken = token,
                latitude = location.latitude.toString(),
                longitude = location.longitude.toString(),
                organizationId = PARTNER_ID
            ).getOrThrow()
            val resultOffers = repository.offersByOrganization(
                accessToken = token,
                latitude = location.latitude.toString(),
                longitude = location.longitude.toString(),
                organizationId = PARTNER_ID
            ).getOrThrow()?.map(offerMapper::map) ?: emptyList()
            partnerMapper.map(resultOrganization!!, resultShop!!.first(), resultOffers)
        }
    }
}