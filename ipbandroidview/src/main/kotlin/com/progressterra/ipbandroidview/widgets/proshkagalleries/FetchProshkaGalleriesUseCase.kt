package com.progressterra.ipbandroidview.widgets.proshkagalleries

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCardState
import com.progressterra.ipbandroidview.processes.goods.GoodsUseCase
import kotlinx.coroutines.flow.Flow

interface FetchProshkaGalleriesUseCase {

    suspend operator fun invoke(categoryId: String): Result<Flow<PagingData<ProshkaStoreCardState>>>

    class Base(
        private val goodsUseCase: GoodsUseCase
    ) : FetchProshkaGalleriesUseCase {

        override suspend fun invoke(categoryId: String): Result<Flow<PagingData<ProshkaStoreCardState>>> =
            goodsUseCase(categoryId)
    }

    companion object {

        const val HOT = "ef1f4abf-5060-4f19-be1c-4a3f56beb89d"

        const val NEW = "04d76bfe-3a6c-44fc-ab56-13b0fce61287"
    }
}