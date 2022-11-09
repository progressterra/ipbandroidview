package com.progressterra.ipbandroidview.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.dto.Goods
import kotlinx.coroutines.flow.Flow

interface GoodsUseCase {

    fun goods(categoryId: String): Result<Flow<PagingData<Goods>>>

    class Base(
        private val source: GoodsSource
    ) : GoodsUseCase {

        override fun goods(categoryId: String): Result<Flow<PagingData<Goods>>> = runCatching {
            source.updateCategory(categoryId)
            Pager(PagingConfig(DomainConstants.PAGE_SIZE)) {
                source
            }.flow
        }
    }
}