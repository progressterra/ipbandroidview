package com.progressterra.ipbandroidview.processes.goods

import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.product.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.product.models.ProductView
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase

interface FetchProductsUseCase {

    suspend operator fun invoke(
        filterAndSort: FilterAndSort
    ): Result<List<ProductView>>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val productRepo: ProductRepository, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources,
    ) : FetchProductsUseCase, AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources) {

        override suspend fun invoke(
            filterAndSort: FilterAndSort
        ): Result<List<ProductView>> = withToken { token ->
            productRepo.productList(token, filterAndSort).getOrThrow() ?: emptyList()
        }
    }
}