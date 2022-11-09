package com.progressterra.ipbandroidview.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.dto.Goods
import kotlinx.coroutines.flow.Flow

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