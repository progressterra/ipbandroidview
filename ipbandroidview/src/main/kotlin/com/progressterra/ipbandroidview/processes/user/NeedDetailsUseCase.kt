package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidview.shared.UserData

interface NeedDetailsUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base : NeedDetailsUseCase {

        override suspend fun invoke(): Result<Unit> = runCatching {
            UserData.needDetails()
        }
    }
}