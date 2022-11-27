package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidview.data.UserData

interface NeedAddressUseCase {

    suspend fun needAddress(): Result<Boolean>

    class Base : NeedAddressUseCase {

        override suspend fun needAddress(): Result<Boolean> = runCatching {
            UserData.needAddress()
        }
    }
}