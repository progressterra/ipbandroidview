package com.progressterra.ipbandroidview.processes.goods

import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.product.models.FilterAndSort
import com.progressterra.ipbandroidview.features.storecard.StoreCardMapper
import com.progressterra.ipbandroidview.features.storecard.StoreCardState

interface FetchGoodsPage {

    suspend operator fun invoke(
        filterAndSort: FilterAndSort
    ): Result<List<StoreCardState>>

    class Base(
        private val mapper: StoreCardMapper,
        private val productRepo: ProductRepository,
    ) : FetchGoodsPage {

        override suspend fun invoke(
            filterAndSort: FilterAndSort
        ): Result<List<StoreCardState>> = runCatching {
            productRepo.productList(filterAndSort).getOrThrow()?.map { mapper.map(it) }
                ?: emptyList()
        }
    }
}