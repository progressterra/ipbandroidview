package com.progressterra.ipbandroidview.processes.withdrawal

import com.progressterra.ipbandroidapi.api.balance.BalanceRepository
import com.progressterra.ipbandroidview.entities.Price
import com.progressterra.ipbandroidview.entities.toPrice
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase

interface FetchWithdrawalUseCase {

    suspend operator fun invoke(): Result<Price>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val balanceRepository: BalanceRepository,
        makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) :
        FetchWithdrawalUseCase,
        AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources) {

        override suspend fun invoke(): Result<Price> = withToken { token ->
            balanceRepository.client(token).getOrThrow()?.amount?.toPrice()!!
        }
    }
}
