package com.progressterra.ipbandroidview.processes.cart

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.cart.models.IncomeDataAddProductAsInstallmentPlan
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.Installment
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.throwOnFailure

interface AddToCartInstallmentUseCase {

    suspend operator fun invoke(
        goodsId: String,
        installment: Installment,
        count: Int = 1
    ): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: CartRepository
    ) : AddToCartInstallmentUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(
            goodsId: String,
            installment: Installment,
            count: Int
        ): Result<Unit> =
            withToken { token ->
                repo.addToCartInstallment(
                    token,
                    IncomeDataAddProductAsInstallmentPlan(
                        idrfNomenclature = goodsId,
                        countMonthPayment = installment.months,
                        amountPaymentInMonth = installment.perMonth.toDouble(),
                        count = 1
                    )
                ).throwOnFailure()
            }
    }
}