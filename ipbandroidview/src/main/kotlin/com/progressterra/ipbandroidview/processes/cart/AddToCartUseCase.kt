package com.progressterra.ipbandroidview.processes.cart

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.cart.models.IncomeDataAddProductFullPrice
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.throwOnFailure

interface AddToCartUseCase {

    suspend operator fun invoke(goodsId: String, count: Int = 1): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: CartRepository
    ) : AddToCartUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(goodsId: String, count: Int): Result<Unit> =
            withToken { token ->
                repo.addToCart(
                    token,
                    IncomeDataAddProductFullPrice(
                        idrfNomenclature = goodsId,
                        count = 1
                    )
                ).throwOnFailure()
            }
    }
}
