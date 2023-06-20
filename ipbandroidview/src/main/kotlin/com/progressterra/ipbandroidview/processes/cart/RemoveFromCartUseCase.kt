package com.progressterra.ipbandroidview.processes.cart

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.cart.models.IncomeDataAddProductFullPrice
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
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
                repo.deleteFromCart(
                    token,
                    IncomeDataAddProductFullPrice(goodsId, 1)
                ).throwOnFailure()
            }
    }

    class Test : RemoveFromCartUseCase {
        override suspend fun invoke(goodsId: String, count: Int): Result<Unit> =
            Result.success(Unit)
    }
}