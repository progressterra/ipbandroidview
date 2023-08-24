package com.progressterra.ipbandroidview.processes.goods

import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface FetchSingleGoodsUseCase {

    suspend operator fun invoke(id: String): Result<StoreCardState>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val productRepository: ProductRepository
    ) : FetchSingleGoodsUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(id: String): Result<StoreCardState> = withToken { token ->
            if (id.isNotEmpty()) {
                productRepository.productByNomenclatureId(token, id).getOrNull()?.toGoodsItem()
                    ?.toStoreCardState() ?: StoreCardState()
            } else {
                StoreCardState()
            }
        }
    }
}