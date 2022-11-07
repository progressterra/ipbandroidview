package com.progressterra.ipbandroidview.domain

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.dto.Goods
import kotlinx.coroutines.flow.Flow

interface RecommendedGoodsUseCase {

    fun recommendedGoods(): Result<Flow<PagingData<Goods>>>

    class Base(
        private val source: GoodsSource
    ) : RecommendedGoodsUseCase {

        override fun recommendedGoods(): Result<Flow<PagingData<Goods>>> = runCatching {
            Log.d("PAGING", "usecase")
            Pager(PagingConfig(DomainConstants.PAGE_SIZE)) {
                source
            }.flow
        }
    }
}