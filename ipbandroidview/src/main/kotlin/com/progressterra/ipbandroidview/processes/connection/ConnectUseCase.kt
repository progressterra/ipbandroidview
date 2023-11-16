package com.progressterra.ipbandroidview.processes.connection

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.IncomeDataIDClient
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources

interface ConnectUseCase {

    suspend operator fun invoke(user: DatingUser): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val imhService: ImhService, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : ConnectUseCase, AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources) {

        override suspend fun invoke(user: DatingUser): Result<Unit> = withToken {
            imhService.connect(
                token = it,
                body = IncomeDataIDClient(user.id)
            )
        }
    }
}