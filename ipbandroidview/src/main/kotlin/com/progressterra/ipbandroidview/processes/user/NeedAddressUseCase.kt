package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidview.shared.UserData

interface NeedAddressUseCase {

    suspend operator fun invoke(): Result<Boolean>

    class Base : NeedAddressUseCase {

        override suspend fun invoke(): Result<Boolean> = runCatching {
            UserData.needAddress()
        }
    }
}