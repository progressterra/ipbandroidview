package com.progressterra.ipbandroidview.domain.usecase

import com.progressterra.ipbandroidapi.user.UserData

interface FetchUserNameUseCase {

    suspend fun fetch(): Result<String>

    class Base : FetchUserNameUseCase {

        override suspend fun fetch(): Result<String> = runCatching {
            "${UserData.userName.name} ${UserData.userName.surname}"
        }
    }
}