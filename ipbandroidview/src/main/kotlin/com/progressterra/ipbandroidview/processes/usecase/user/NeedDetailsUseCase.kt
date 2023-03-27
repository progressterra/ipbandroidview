package com.progressterra.ipbandroidview.processes.usecase.user

import com.progressterra.ipbandroidview.data.UserData

interface NeedDetailsUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base : NeedDetailsUseCase {

        override suspend fun invoke(): Result<Unit> = runCatching {
            UserData.needDetails()
        }
    }
}