package com.progressterra.ipbandroidview.processes.goods

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCardState
import com.progressterra.ipbandroidview.processes.store.FetchFavoriteIds
import kotlinx.coroutines.flow.Flow

interface GoodsUseCase {

    suspend operator fun invoke(categoryId: String): Result<Flow<PagingData<ProshkaStoreCardState>>>

    class Base(
        private val fetchGoodsPage: FetchGoodsPage,
        private val fetchFavoriteIds: FetchFavoriteIds
    ) : GoodsUseCase {

        override suspend fun invoke(categoryId: String): Result<Flow<PagingData<ProshkaStoreCardState>>> =
            runCatching {
                Pager(PagingConfig(10)) {
                    GoodsSource(
                        categoryId = categoryId,
                        fetchGoodsPage = fetchGoodsPage,
                        fetchFavoriteIds = fetchFavoriteIds
                    )
                }.flow
            }
    }
}