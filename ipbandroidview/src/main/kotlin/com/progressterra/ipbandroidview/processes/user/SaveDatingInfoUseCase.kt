package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.RGClientDataPersonalEntity
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface SaveDatingInfoUseCase {

    suspend operator fun invoke(nickName: String, description: String): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val service: ImhService
    ) : SaveDatingInfoUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(
            nickName: String,
            description: String
        ): Result<Unit> = withToken {
            service.clientDataPersonal(
                token = it,
                body = RGClientDataPersonalEntity(
                    nickName = nickName,
                    descriptionAboutMe = description
                )
            )
        }
    }
}