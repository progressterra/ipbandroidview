package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidview.data.UserData

interface UserExistUseCase {

    suspend fun userExist(): Result<Boolean>

    class Base : UserExistUseCase {

        override suspend fun userExist(): Result<Boolean> = runCatching {
            UserData.clientExist
        }
    }
}