package com.progressterra.ipbandroidview.domain.usecase

import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidview.ext.needDetails

interface NeedDetailsUseCase {

    suspend fun needDetails(): Result<Boolean>

    class Base : NeedDetailsUseCase {

        override suspend fun needDetails(): Result<Boolean> = runCatching {
            UserData.needDetails()
        }
    }
}