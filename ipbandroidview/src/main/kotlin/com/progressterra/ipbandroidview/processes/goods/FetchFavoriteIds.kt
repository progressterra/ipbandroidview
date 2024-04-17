package com.progressterra.ipbandroidview.processes.goods

import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase

interface FetchFavoriteIds {

    suspend operator fun invoke(): Result<List<String>>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) :
        FetchFavoriteIds,
        AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources) {

        override suspend fun invoke(): Result<List<String>> = Result.success(emptyList())
    }
}
