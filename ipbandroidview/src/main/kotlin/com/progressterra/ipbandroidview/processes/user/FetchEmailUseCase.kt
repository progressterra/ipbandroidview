package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidview.shared.UserData

interface FetchEmailUseCase {

    suspend operator fun invoke(): Result<String>

    class Base : FetchEmailUseCase {

        override suspend fun invoke(): Result<String> = runCatching {
            UserData.email
        }
    }
}