package com.progressterra.ipbandroidview.processes.goods

import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources

interface FetchSingleGoodsUseCase {

    suspend operator fun invoke(id: String): Result<StoreCardState>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val productRepository: ProductRepository, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : FetchSingleGoodsUseCase, AbstractTokenUseCase(obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

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