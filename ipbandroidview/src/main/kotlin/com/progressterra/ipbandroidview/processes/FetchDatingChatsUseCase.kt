package com.progressterra.ipbandroidview.processes

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.entities.DatingChat
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractCacheTokenUseCase
import com.progressterra.ipbandroidview.shared.CacheUseCase
import com.progressterra.ipbandroidview.shared.ManageResources
import kotlinx.coroutines.flow.Flow

interface FetchDatingChatsUseCase : CacheUseCase<Flow<PagingData<DatingChat>>> {

    suspend operator fun invoke()

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val pagingUseCase: DatingChatsPagingUseCase, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : FetchDatingChatsUseCase,
        AbstractCacheTokenUseCase<Flow<PagingData<DatingChat>>>(obtainAccessToken, makeToastUseCase,
            manageResources
        ) {

        override suspend fun invoke() {
            withCache { _ ->
                pagingUseCase().getOrThrow()
            }
        }
    }
}

