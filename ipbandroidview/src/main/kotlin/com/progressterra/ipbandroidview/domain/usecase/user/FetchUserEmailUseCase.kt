package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidview.data.UserData

interface FetchUserEmailUseCase {

    suspend operator fun invoke(): Result<String>

    class Base : FetchUserEmailUseCase {

        override suspend fun invoke(): Result<String> = runCatching {
            UserData.email
        }
    }
}