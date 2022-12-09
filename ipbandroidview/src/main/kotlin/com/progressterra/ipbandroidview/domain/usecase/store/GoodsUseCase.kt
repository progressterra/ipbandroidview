package com.progressterra.ipbandroidview.domain.usecase.store

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.progressterra.ipbandroidview.domain.AppSettings
import com.progressterra.ipbandroidview.domain.GoodsSource
import com.progressterra.ipbandroidview.model.store.StoreGoods

interface GoodsUseCase {

    suspend operator fun invoke(categoryId: String): Result<Pager<Int, StoreGoods>>

    class Base(
        private val goodsPageUseCase: GoodsPageUseCase,
        private val favoriteIdsUseCase: FavoriteIdsUseCase
    ) : GoodsUseCase {

        override suspend fun invoke(categoryId: String): Result<Pager<Int, StoreGoods>> = runCatching {
            Pager(PagingConfig(AppSettings.PAGE_SIZE)) {
                GoodsSource(
                    categoryId = categoryId,
                    goodsPageUseCase = goodsPageUseCase,
                    favoriteIdsUseCase = favoriteIdsUseCase
                )
            }
        }
    }
}