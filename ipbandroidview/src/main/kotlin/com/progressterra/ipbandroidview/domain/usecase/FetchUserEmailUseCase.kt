package com.progressterra.ipbandroidview.domain.usecase

import com.progressterra.ipbandroidapi.user.UserData

interface FetchUserEmailUseCase {

    suspend fun fetch(): Result<String>

    class Base : FetchUserEmailUseCase {

        override suspend fun fetch(): Result<String> = runCatching {
            UserData.email
        }
    }
}