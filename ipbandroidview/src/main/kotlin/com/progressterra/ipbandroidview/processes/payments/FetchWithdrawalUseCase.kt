package com.progressterra.ipbandroidview.processes.payments

import com.progressterra.ipbandroidapi.api.balance.BalanceRepository
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.entities.toSimplePrice
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface FetchWithdrawalUseCase {

    suspend operator fun invoke(): Result<SimplePrice>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val balanceRepository: BalanceRepository, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : FetchWithdrawalUseCase, AbstractTokenUseCase(
        obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(): Result<SimplePrice> = withToken { token ->
            balanceRepository.client(token).getOrThrow()?.amount?.toSimplePrice()!!
        }
    }
}