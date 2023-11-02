package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.IncomeDataIDConnect
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface AcceptConnectUseCase {

    suspend operator fun invoke(user: DatingUser): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val imhService: ImhService, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : AcceptConnectUseCase, AbstractTokenUseCase(obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(user: DatingUser): Result<Unit> = withToken {
            imhService.connectApprove(
                token = it,
                body = IncomeDataIDConnect(idConnect = user.id)
            )
        }
    }
}