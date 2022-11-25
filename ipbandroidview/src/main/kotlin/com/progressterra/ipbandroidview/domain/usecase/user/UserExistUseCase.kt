package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidapi.user.UserData

interface UserExistUseCase {

    suspend fun userExist(): Result<Boolean>

    class Base : UserExistUseCase {

        override suspend fun userExist(): Result<Boolean> = runCatching {
            UserData.clientExist
        }
    }
}