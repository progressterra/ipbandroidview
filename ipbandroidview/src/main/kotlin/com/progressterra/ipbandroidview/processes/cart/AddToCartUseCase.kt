package com.progressterra.ipbandroidview.processes.cart

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.iecommerce.model.ParamGoodsToECommers
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.ext.throwOnFailure
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.Constants

interface AddToCartUseCase {

    suspend operator fun invoke(goodsId: String, count: Int = 1): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: CartRepository
    ) : AddToCartUseCase, AbstractUseCase(scrmRepository, provideLocation) {

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