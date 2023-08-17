package com.progressterra.ipbandroidview.processes.payments

import com.progressterra.ipbandroidapi.api.balance.BalanceRepository
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.entities.toSimplePrice
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface FetchWithdrawalUseCase {

    suspend operator fun invoke(): Result<SimplePrice>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val balanceRepository: BalanceRepository
    ) : FetchWithdrawalUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<SimplePrice> = withToken { token ->
            balanceRepository.client(token).getOrThrow()?.amount?.toSimplePrice()!!
        }
    }
}