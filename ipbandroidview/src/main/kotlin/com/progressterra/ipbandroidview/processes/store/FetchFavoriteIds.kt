package com.progressterra.ipbandroidview.processes.store

import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface FetchFavoriteIds {

    suspend operator fun invoke(): Result<List<String>>

    class Base(
        obtainAccessToken: ObtainAccessToken
    ) : FetchFavoriteIds, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<List<String>> = Result.success(emptyList())
    }
}