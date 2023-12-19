package com.progressterra.ipbandroidview.processes.partner

import com.progressterra.ipbandroidapi.api.collaboration.CollaborationRepository
import com.progressterra.ipbandroidview.entities.Partner
import com.progressterra.ipbandroidview.entities.toOffer
import com.progressterra.ipbandroidview.entities.toPartner
import com.progressterra.ipbandroidview.processes.location.ProvideLocationUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase

interface FetchPartnerUseCase {

    suspend operator fun invoke(): Result<Partner>

    class Base(
        private val repository: CollaborationRepository,
        makeToastUseCase: MakeToastUseCase,
        private val provideLocationUseCase: ProvideLocationUseCase,
        manageResources: ManageResources,
        obtainAccessToken: ObtainAccessToken
    ) : FetchPartnerUseCase,
        AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources) {

        override suspend fun invoke(): Result<Partner> = withToken { token ->
            val location = provideLocationUseCase().getOrNull()
            val resultOrganization = repository.organizationById(
                accessToken = token,
                latitude = location?.latitude?.toString() ?: "0.0",
                longitude = location?.longitude?.toString() ?: "0.0",
                organizationId = "7eb4858d-962a-494b-b93f-24cdd0fca2e1"
            ).getOrThrow()
            val resultShop = repository.organizationShops(
                accessToken = token,
                latitude = location?.latitude?.toString() ?: "0.0",
                longitude = location?.longitude?.toString() ?: "0.0",
                organizationId = "7eb4858d-962a-494b-b93f-24cdd0fca2e1"
            ).getOrThrow()
            val resultOffers = repository.offersByOrganization(
                accessToken = token,
                latitude = location?.latitude?.toString() ?: "0.0",
                longitude = location?.longitude?.toString() ?: "0.0",
                organizationId = "7eb4858d-962a-494b-b93f-24cdd0fca2e1"
            ).getOrThrow()?.map { it.toOffer() } ?: emptyList()
            resultOrganization!!.toPartner(resultShop!!.first(), resultOffers)
        }
    }
}