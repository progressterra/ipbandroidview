package com.progressterra.ipbandroidview.features.bonuses

import com.progressterra.ipbandroidapi.api.balance.BalanceRepository
import com.progressterra.ipbandroidapi.api.ibonus.IBonusRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidapi.ext.parseToDate
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface BonusesUseCase {

    suspend operator fun invoke(): Result<BonusesState>

    class Base(
        sCRMRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        manageResources: ManageResources,
        private val bonusesRepository: IBonusRepository,
        private val balanceRepository: BalanceRepository
    ) : BonusesUseCase, AbstractTokenUseCase(sCRMRepository, provideLocation) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun invoke(): Result<BonusesState> = withToken { token ->
            val response = bonusesRepository.getGeneralInfo(token).getOrThrow()
            BonusesState(
                bonuses = response?.currentQuantity?.toString() ?: noData,
                burningQuantity = response?.forBurningQuantity?.toString() ?: noData,
                burningDate = response?.dateBurning?.parseToDate()?.format("dd.MM") ?: noData,
                roubles = balanceRepository.client(token).getOrThrow()?.amount?.toInt()?.toString() ?: ""
            )
        }
    }
}