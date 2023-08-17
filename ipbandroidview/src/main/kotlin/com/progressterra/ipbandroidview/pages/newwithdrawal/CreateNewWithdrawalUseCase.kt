package com.progressterra.ipbandroidview.pages.newwithdrawal

import com.progressterra.ipbandroidapi.api.payment.PaymentRepository
import com.progressterra.ipbandroidapi.api.payment.models.DHPaymentEntityIncome
import com.progressterra.ipbandroidview.features.bankcard.BankCardState
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.throwOnFailure

interface CreateNewWithdrawalUseCase {

    suspend operator fun invoke(bankCard: BankCardState, amount: String): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val paymentRepository: PaymentRepository
    ) : AbstractTokenUseCase(obtainAccessToken), CreateNewWithdrawalUseCase {

        override suspend fun invoke(bankCard: BankCardState, amount: String): Result<Unit> =
            withToken { token ->
                paymentRepository.clientAreaPayment(
                    accessToken = token,
                    body = DHPaymentEntityIncome(
                        idPaymentData = bankCard.id,
                        amount = amount.toDouble()
                    )
                ).throwOnFailure()
            }
    }
}