package com.progressterra.ipbandroidview.features.bonuses

import com.progressterra.ipbandroidapi.api.balance.BalanceRepository
import com.progressterra.ipbandroidapi.api.ibonus.IBonusRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface BonusesUseCase {

    suspend operator fun invoke(): Result<BonusesState>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        manageResources: ManageResources,
        private val bonusesRepository: IBonusRepository,
        private val balanceRepository: BalanceRepository
    ) : BonusesUseCase, AbstractTokenUseCase(obtainAccessToken) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun invoke(): Result<BonusesState> = withToken { token ->
            val response = bonusesRepository.getGeneralInfo(token).getOrThrow()
            BonusesState(
                bonuses = response?.currentQuantity?.toInt()?.toString() ?: noData,
                roubles = balanceRepository.client(token).getOrThrow()?.amount?.toInt()?.toString()
                    ?: ""
            )
        }
    }
}