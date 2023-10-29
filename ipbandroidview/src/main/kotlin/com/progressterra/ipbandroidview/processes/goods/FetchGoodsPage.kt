package com.progressterra.ipbandroidview.processes.goods

import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.product.models.FilterAndSort
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface FetchGoodsPage {

    suspend operator fun invoke(
        filterAndSort: FilterAndSort
    ): Result<List<StoreCardState>>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val productRepo: ProductRepository, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources,
    ) : FetchGoodsPage, AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources) {

        override suspend fun invoke(
            filterAndSort: FilterAndSort
        ): Result<List<StoreCardState>> = withToken { token ->
            productRepo.productList(token, filterAndSort).getOrThrow()
                ?.map { it.toGoodsItem().toStoreCardState() }
                ?: emptyList()
        }
    }
}