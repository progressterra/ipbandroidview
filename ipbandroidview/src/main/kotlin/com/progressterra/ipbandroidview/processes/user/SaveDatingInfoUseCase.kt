package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.RGClientDataPersonalEntity
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources

interface SaveDatingInfoUseCase {

    suspend operator fun invoke(description: String): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val service: ImhService, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : SaveDatingInfoUseCase, AbstractTokenUseCase(
        obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(description: String): Result<Unit> = withToken {
            service.clientDataPersonal(
                token = it,
                body = RGClientDataPersonalEntity(
                    nickName = "",
                    descriptionAboutMe = description
                )
            )
        }
    }
}