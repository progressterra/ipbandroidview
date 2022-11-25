package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidapi.user.UserData

interface FetchUserPhoneUseCase {

    suspend fun fetch(): Result<String>

    class Base : FetchUserPhoneUseCase {

        override suspend fun fetch(): Result<String> = runCatching {
            UserData.phone
        }
    }
}