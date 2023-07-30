package com.progressterra.ipbandroidview.processes.goods

import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidview.entities.GoodsFilter
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.PagingUseCase

interface GoodsUseCase : PagingUseCase<GoodsFilter, StoreCardState> {

    class Base(
        private val obtainAccessToken: ObtainAccessToken,
        private val productRepository: ProductRepository
    ) : GoodsUseCase, PagingUseCase.Abstract<GoodsFilter, StoreCardState>() {

        override fun createSource() = GoodsSource(obtainAccessToken, productRepository)
    }
}
