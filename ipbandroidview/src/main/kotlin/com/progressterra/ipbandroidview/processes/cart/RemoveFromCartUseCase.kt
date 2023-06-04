package com.progressterra.ipbandroidview.processes.cart

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.iecommerce.model.ParamGoodsToECommers
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.Constants
import com.progressterra.ipbandroidview.shared.throwOnFailure

interface RemoveFromCartUseCase {

    suspend operator fun invoke(goodsId: String, count: Int = 1): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: CartRepository
    ) : RemoveFromCartUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(goodsId: String, count: Int): Result<Unit> =
            withToken { token ->
                repo.removeProductFromCart(
                    ParamGoodsToECommers(
                        idGoodsInventory = goodsId,
                        count = 1,
                        idSellerAmbassador = Constants.DEFAULT_ID
                    ),
                    token
                ).throwOnFailure()
            }
    }

    class Test : RemoveFromCartUseCase {
        override suspend fun invoke(goodsId: String, count: Int): Result<Unit> =
            Result.success(Unit)
    }
}