package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidview.data.UserData

interface LogoutUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base : LogoutUseCase {

        override suspend fun invoke(): Result<Unit> = runCatching {
            UserData.clearUser()
        }
    }
}