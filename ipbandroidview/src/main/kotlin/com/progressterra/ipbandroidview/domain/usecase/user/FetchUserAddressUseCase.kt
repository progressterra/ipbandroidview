package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidapi.user.UserData

interface FetchUserAddressUseCase {

    suspend fun fetch(): Result<String>

    class Base : FetchUserAddressUseCase {

        override suspend fun fetch(): Result<String> = runCatching { UserData.address }
    }
}