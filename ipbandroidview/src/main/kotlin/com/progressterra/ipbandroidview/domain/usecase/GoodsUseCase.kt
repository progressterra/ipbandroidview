package com.progressterra.ipbandroidview.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.progressterra.ipbandroidview.domain.DomainConstants
import com.progressterra.ipbandroidview.domain.GoodsSource
import com.progressterra.ipbandroidview.model.StoreGoods

interface GoodsUseCase {

    fun goods(categoryId: String): Result<Pager<Int, StoreGoods>>

    class Base(
        private val goodsPageUseCase: GoodsPageUseCase,
        private val favoriteIdsUseCase: FavoriteIdsUseCase
    ) : GoodsUseCase {

        override fun goods(categoryId: String): Result<Pager<Int, StoreGoods>> = runCatching {
            Pager(PagingConfig(DomainConstants.PAGE_SIZE)) {
                GoodsSource(
                    categoryId = categoryId,
                    goodsPageUseCase = goodsPageUseCase,
                    favoriteIdsUseCase = favoriteIdsUseCase
                )
            }
        }
    }
}