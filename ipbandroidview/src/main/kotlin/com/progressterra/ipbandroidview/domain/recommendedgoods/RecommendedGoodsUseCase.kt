package com.progressterra.ipbandroidview.domain.recommendedgoods

import android.util.Log
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
            Log.d("PAGING", "usecase")
            Pager(PagingConfig(DomainConstants.PAGE_SIZE)) {
                source
            }.flow
        }
    }
}