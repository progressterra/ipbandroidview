package com.progressterra.ipbandroidview.domain.usecase

import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidview.ext.needAddress

interface NeedAddressUseCase {

    suspend fun needAddress(): Result<Boolean>

    class Base : NeedAddressUseCase {

        override suspend fun needAddress(): Result<Boolean> = runCatching {
            UserData.needAddress()
        }
    }
}