package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidview.shared.UserData

interface UserExistsUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base : UserExistsUseCase {

        override suspend fun invoke(): Result<Unit> = runCatching {
            UserData.clientExist
        }
    }
 }