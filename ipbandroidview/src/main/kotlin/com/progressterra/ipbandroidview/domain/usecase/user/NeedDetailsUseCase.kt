package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidview.data.UserData

interface NeedDetailsUseCase {

    suspend operator fun invoke(): Result<Boolean>

    class Base : NeedDetailsUseCase {

        override suspend fun invoke(): Result<Boolean> = runCatching {
            UserData.needDetails()
        }
    }
}