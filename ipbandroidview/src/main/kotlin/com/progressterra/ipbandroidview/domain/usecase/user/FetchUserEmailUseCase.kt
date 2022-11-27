package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidview.data.UserData

interface FetchUserEmailUseCase {

    suspend fun fetch(): Result<String>

    class Base : FetchUserEmailUseCase {

        override suspend fun fetch(): Result<String> = runCatching {
            UserData.email
        }
    }
}