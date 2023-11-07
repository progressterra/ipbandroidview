package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidapi.api.cart.CartService
import com.progressterra.ipbandroidapi.api.cart.models.IncomeDataImplementBonuses
import com.progressterra.ipbandroidapi.api.cart.models.StatusResult
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface UseBonusesUseCase {

    suspend operator fun invoke(sum: Int): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val cartRepository: CartService, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources),
        UseBonusesUseCase {

        override suspend fun invoke(sum: Int): Result<Unit> = withToken { token ->
            cartRepository.useBonuses(
                token, IncomeDataImplementBonuses(sum.toDouble())
            ).also {
                if (it.result?.status != StatusResult.SUCCESS) throw ToastedException(
                    it.result?.message ?: ""
                )
            }
        }
    }
}
