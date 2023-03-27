package com.progressterra.ipbandroidview.processes.usecase.store

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.progressterra.ipbandroidview.processes.AppSettings
import com.progressterra.ipbandroidview.processes.GoodsSource
import com.progressterra.ipbandroidview.composable.component.StoreCardComponentState

interface GoodsUseCase {

    suspend operator fun invoke(categoryId: String): Result<Pager<Int, StoreCardComponentState>>

    class Base(
        private val fetchGoodsPage: FetchGoodsPage,
        private val fetchFavoriteIds: FetchFavoriteIds
    ) : GoodsUseCase {

        override suspend fun invoke(categoryId: String): Result<Pager<Int, StoreCardComponentState>> =
            runCatching {
                Pager(PagingConfig(AppSettings.PAGE_SIZE)) {
                    GoodsSource(
                        categoryId = categoryId,
                        fetchGoodsPage = fetchGoodsPage,
                        fetchFavoriteIds = fetchFavoriteIds
                    )
                }
            }
    }
}