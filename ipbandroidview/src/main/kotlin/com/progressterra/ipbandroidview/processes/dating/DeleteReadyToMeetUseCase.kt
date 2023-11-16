package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources

interface DeleteReadyToMeetUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val imhService: ImhService, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : DeleteReadyToMeetUseCase, AbstractTokenUseCase(obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(): Result<Unit> =
            withToken { token ->
                imhService.clientDataReadyMeetDelete(
                    token = token
                )
            }
    }
}