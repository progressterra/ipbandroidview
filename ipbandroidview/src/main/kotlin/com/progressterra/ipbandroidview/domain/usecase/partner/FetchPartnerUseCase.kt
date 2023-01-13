package com.progressterra.ipbandroidview.domain.usecase.partner

import android.util.Log
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
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository
    ) : FetchPartnerUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<Partner> = withToken { token ->
            Log.d("PARTNER", "fetch partner start")
            val resultOrganization = repository.organizationById(
                accessToken = token,
                latitude = "",
                longitude = "",
                organizationId = PARTNER_ID
            ).getOrThrow()
            Log.d("PARTNER", "fetch org: $resultOrganization")
            val resultShop = repository.organizationShops(
                accessToken = token,
                latitude = "",
                longitude = "",
                organizationId = PARTNER_ID
            ).getOrThrow()
            Log.d("PARTNER", "fetch shop: $resultShop")
            val resultOffers = repository.offersByOrganization(
                accessToken = token,
                latitude = "",
                longitude = "",
                organizationId = PARTNER_ID
            ).getOrThrow()?.map(offerMapper::map) ?: emptyList()
            Log.d("PARTNER", "fetch offers: $resultOffers")
            partnerMapper.map(resultOrganization!!, resultShop!!.first(), resultOffers)
        }
    }
}