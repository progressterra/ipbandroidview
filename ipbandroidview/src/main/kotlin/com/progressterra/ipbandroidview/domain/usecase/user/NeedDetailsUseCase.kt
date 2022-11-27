package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidview.data.UserData

interface NeedDetailsUseCase {

    suspend fun needDetails(): Result<Boolean>

    class Base : NeedDetailsUseCase {

        override suspend fun needDetails(): Result<Boolean> = runCatching {
            UserData.needDetails()
        }
    }
}