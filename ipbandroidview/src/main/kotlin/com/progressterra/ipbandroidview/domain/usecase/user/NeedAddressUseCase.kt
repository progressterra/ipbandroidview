package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidview.data.UserData

interface NeedAddressUseCase {

    suspend operator fun invoke(): Result<Boolean>

    class Base : NeedAddressUseCase {

        override suspend fun invoke(): Result<Boolean> = runCatching {
            UserData.needAddress()
        }
    }
}