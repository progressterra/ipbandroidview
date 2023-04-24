package com.progressterra.ipbandroidview.processes.goods

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import kotlinx.coroutines.flow.Flow

interface GoodsUseCase {

    suspend operator fun invoke(categoryId: String): Result<Flow<PagingData<StoreCardState>>>

    class Base(
        private val fetchGoodsPage: FetchGoodsPage
    ) : GoodsUseCase {

        override suspend fun invoke(categoryId: String): Result<Flow<PagingData<StoreCardState>>> =
            runCatching {
                Pager(PagingConfig(10)) {
                    GoodsSource(
                        categoryId = categoryId,
                        fetchGoodsPage = fetchGoodsPage
                    )
                }.flow
            }
    }
}