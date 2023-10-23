package com.progressterra.ipbandroidview.processes

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.entities.DatingChat
import com.progressterra.ipbandroidview.shared.AbstractCacheTokenUseCase
import com.progressterra.ipbandroidview.shared.CacheUseCase
import kotlinx.coroutines.flow.Flow

interface FetchDatingChatsUseCase : CacheUseCase<Flow<PagingData<DatingChat>>> {

    suspend operator fun invoke()

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val pagingUseCase: DatingChatsPagingUseCase
    ) : FetchDatingChatsUseCase,
        AbstractCacheTokenUseCase<Flow<PagingData<DatingChat>>>(obtainAccessToken) {

        override suspend fun invoke() {
            withCache { _ ->
                pagingUseCase().getOrThrow()
            }
        }
    }
}

