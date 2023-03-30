package com.progressterra.ipbandroidview.processes.goods

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.entities.GoodsItem
import com.progressterra.ipbandroidview.processes.AppSettings
import com.progressterra.ipbandroidview.processes.GoodsSource
import com.progressterra.ipbandroidview.processes.usecase.store.FetchFavoriteIds
import kotlinx.coroutines.flow.Flow

interface GoodsUseCase {

    suspend operator fun invoke(categoryId: String): Result<Flow<PagingData<GoodsItem>>>

    class Base(
        private val fetchGoodsPage: FetchGoodsPage,
        private val fetchFavoriteIds: FetchFavoriteIds
    ) : GoodsUseCase {

        override suspend fun invoke(categoryId: String): Result<Flow<PagingData<GoodsItem>>> =
            runCatching {
                Pager<Int, GoodsItem>(PagingConfig(AppSettings.PAGE_SIZE)) {
                    GoodsSource(
                        categoryId = categoryId,
                        fetchGoodsPage = fetchGoodsPage,
                        fetchFavoriteIds = fetchFavoriteIds
                    )
                }.flow
            }
    }
}