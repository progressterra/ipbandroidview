package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidapi.api.cart.CartService
import com.progressterra.ipbandroidapi.api.cart.models.StatusResult
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface CancelUseBonusesUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val repo: CartService, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources),
        CancelUseBonusesUseCase {

        override suspend fun invoke(): Result<Unit> = withToken { token ->
            repo.cancelBonuses(token).also {
                if (it.result?.status != StatusResult.SUCCESS) throw ToastedException(
                    it.result?.message ?: ""
                )
            }
        }
    }
}