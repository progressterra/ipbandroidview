package com.progressterra.ipbandroidview.processes.goods

import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.product.models.FilterAndSort
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface FetchGoodsPage {

    suspend operator fun invoke(
        filterAndSort: FilterAndSort
    ): Result<List<StoreCardState>>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val productRepo: ProductRepository,
    ) : FetchGoodsPage, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(
            filterAndSort: FilterAndSort
        ): Result<List<StoreCardState>> = withToken { token ->
            productRepo.productList(token, filterAndSort).getOrThrow()
                ?.map { it.toGoodsItem().toStoreCardState() }
                ?: emptyList()
        }
    }
}