package com.progressterra.ipbandroidview.features.bonuses

import com.progressterra.ipbandroidapi.api.ibonus.IBonusRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidapi.ext.parseToDate
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface BonusesUseCase {

    suspend operator fun invoke(): Result<BonusesState>

    class Base(
        sCRMRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        manageResources: ManageResources,
        private val bonusesRepository: IBonusRepository
    ) : BonusesUseCase, AbstractUseCase(sCRMRepository, provideLocation) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun invoke(): Result<BonusesState> = withToken { token ->
            val response = bonusesRepository.getGeneralInfo(token).getOrThrow()
            BonusesState(
                bonuses = response?.currentQuantity?.toString() ?: noData,
                burningQuantity = response?.forBurningQuantity?.toString() ?: noData,
                burningDate = response?.dateBurning?.parseToDate()?.format("dd.MM") ?: noData,
                canWithdraw = "10 000₽",
                rate = "1₽ = 1 бонус",
                loan = "50 000₽",
            )
        }
    }

    class Test : BonusesUseCase {

        override suspend fun invoke(): Result<BonusesState> = runCatching {
            BonusesState(
                bonuses = "1234",
                burningQuantity = "5",
                burningDate = "10.10",
                canWithdraw = "5 000₽",
                rate = "1₽ = 1 бонус",
                loan = "50 000₽",
            )
        }
    }
}