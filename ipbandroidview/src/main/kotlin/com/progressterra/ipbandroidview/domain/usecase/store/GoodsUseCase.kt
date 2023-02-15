package com.progressterra.ipbandroidview.domain.usecase.store

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.progressterra.ipbandroidview.domain.AppSettings
import com.progressterra.ipbandroidview.domain.GoodsSource
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