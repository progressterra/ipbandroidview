package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidview.data.UserData

interface UserExistsUseCase {

    suspend operator fun invoke(): Result<Boolean>

    class Base : UserExistsUseCase {

        override suspend fun invoke(): Result<Boolean> = runCatching {
            UserData.clientExist
        }
    }
}