package com.progressterra.ipbandroidview.processes.usecase.user

import com.progressterra.ipbandroidview.shared.UserData

interface LogoutUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base : LogoutUseCase {

        override suspend fun invoke(): Result<Unit> = runCatching {
            UserData.clearUser()
        }
    }
}