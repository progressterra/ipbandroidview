package com.progressterra.ipbandroidview.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.progressterra.ipbandroidview.domain.DomainConstants
import com.progressterra.ipbandroidview.domain.GoodsSource
import com.progressterra.ipbandroidview.model.Goods

interface GoodsUseCase {

    fun goods(categoryId: String): Result<Pager<Int, Goods>>

    class Base(private val goodsPageUseCase: GoodsPageUseCase) : GoodsUseCase {

        override fun goods(categoryId: String): Result<Pager<Int, Goods>> = runCatching {
            Pager(PagingConfig(DomainConstants.PAGE_SIZE)) {
                GoodsSource(categoryId = categoryId, goodsPageUseCase = goodsPageUseCase)
            }
        }
    }
}