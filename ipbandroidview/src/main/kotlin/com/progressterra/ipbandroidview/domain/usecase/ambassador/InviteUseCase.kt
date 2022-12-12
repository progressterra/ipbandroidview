package com.progressterra.ipbandroidview.domain.usecase.ambassador

import com.progressterra.ipbandroidapi.api.ambassadorinvite.AmbassadorInviteRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.model.UserInvite

interface InviteUseCase {

    suspend operator fun invoke(): Result<UserInvite>

    class Base(
        manageResources: ManageResources,
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val repo: AmbassadorInviteRepository
    ) : InviteUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun invoke(): Result<UserInvite> = withToken { token ->
            val response = repo.getInviteInfo(token).getOrThrow()
            UserInvite(
                promoCode = response?.promocode ?: noData,
                text = response?.textInvite ?: noData
            )
        }
    }
}