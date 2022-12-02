package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidview.data.UserData


interface FetchUserIdUseCase {

    suspend operator fun invoke(): Result<String>

    class Base : FetchUserIdUseCase {

        override suspend fun invoke(): Result<String> = runCatching {
            UserData.idUnique
        }
    }
}