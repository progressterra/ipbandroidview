package com.progressterra.ipbandroidview.pages.withdrawal

import com.progressterra.ipbandroidapi.api.balance.BalanceRepository
import com.progressterra.ipbandroidview.entities.toSimplePrice
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface FetchWithdrawalUseCase {

    suspend operator fun invoke(): Result<String>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val balanceRepository: BalanceRepository
    ) : FetchWithdrawalUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<String> = withToken { token ->
            balanceRepository.client(token).getOrThrow()?.amount?.toSimplePrice()?.toString()!!
        }
    }
}