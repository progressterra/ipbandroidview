package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidview.data.UserData

interface UserExistUseCase {

    suspend operator fun invoke(): Result<Boolean>

    class Base : UserExistUseCase {

        override suspend fun invoke(): Result<Boolean> = runCatching {
            UserData.clientExist
        }
    }
}