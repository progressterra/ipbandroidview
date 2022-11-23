package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.Constants
import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.iecommerce.model.ParamGoodsToECommers
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation

interface FastAddToCartUseCase {

    suspend fun add(goodsId: String, count: Int = 1): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: CartRepository
    ) : FastAddToCartUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun add(goodsId: String, count: Int): Result<Unit> = withToken { token ->
            repo.fastAddToCart(
                token,
                ParamGoodsToECommers(
                    idGoodsInventory = goodsId,
                    count = 1,
                    idSellerAmbassador = Constants.EMPTY_ID
                )
            ).onFailure { throw it }
        }
    }
}