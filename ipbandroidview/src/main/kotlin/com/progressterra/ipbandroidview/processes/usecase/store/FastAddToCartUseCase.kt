package com.progressterra.ipbandroidview.processes.usecase.store

import com.progressterra.ipbandroidview.data.Constants
import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.iecommerce.model.ParamGoodsToECommers
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.ext.throwOnFailure

interface FastAddToCartUseCase {

    suspend operator fun invoke(goodsId: String, count: Int = 1): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: CartRepository
    ) : FastAddToCartUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(goodsId: String, count: Int): Result<Unit> =
            withToken { token ->
                repo.fastAddToCart(
                    token,
                    ParamGoodsToECommers(
                        idGoodsInventory = goodsId,
                        count = 1,
                        idSellerAmbassador = Constants.DEFAULT_ID
                    )
                ).throwOnFailure()
            }
    }
}