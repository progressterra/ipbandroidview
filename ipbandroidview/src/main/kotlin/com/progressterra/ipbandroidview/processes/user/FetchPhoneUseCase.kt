package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidview.shared.UserData

interface FetchPhoneUseCase {

    suspend operator fun invoke(): Result<String>

    class Base : FetchPhoneUseCase {

        override suspend fun invoke(): Result<String> = runCatching {
            UserData.phone
        }
    }
}