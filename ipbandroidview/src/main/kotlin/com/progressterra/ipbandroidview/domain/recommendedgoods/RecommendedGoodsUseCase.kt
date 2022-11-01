package com.progressterra.ipbandroidview.domain.recommendedgoods

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.domain.DomainConstants
import com.progressterra.ipbandroidview.dto.GoodsCard
import kotlinx.coroutines.flow.Flow

interface RecommendedGoodsUseCase {

    fun recommendedGoods(): Result<Flow<PagingData<GoodsCard>>>

    class Base(
        private val source: RecommendedGoodsSource
    ) : RecommendedGoodsUseCase {

        override fun recommendedGoods(): Result<Flow<PagingData<GoodsCard>>> = runCatching {
            Pager(PagingConfig(DomainConstants.PAGE_SIZE)) {
                source
            }.flow
        }
    }
}