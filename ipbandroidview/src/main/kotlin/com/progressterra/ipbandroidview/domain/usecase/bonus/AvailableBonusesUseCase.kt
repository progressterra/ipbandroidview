package com.progressterra.ipbandroidview.domain.usecase.bonus

import com.progressterra.ipbandroidapi.api.ibonus.IBonusRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidapi.ext.parseToDate
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.component.BonusesState
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ProvideLocation

interface AvailableBonusesUseCase {

    suspend operator fun invoke(): Result<BonusesState>

    class Base(
        sCRMRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        manageResources: ManageResources,
        private val bonusesRepository: IBonusRepository
    ) : AvailableBonusesUseCase, AbstractUseCase(sCRMRepository, provideLocation) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun invoke(): Result<BonusesState> = withToken { token ->
            val response = bonusesRepository.getGeneralInfo(token).getOrThrow()
            BonusesState(
                bonuses = response?.currentQuantity?.toString() ?: noData,
                burningQuantity = response?.forBurningQuantity?.toString() ?: noData,
                burningDate = response?.dateBurning?.parseToDate()?.format("dd.MM") ?: noData
            )
        }
    }
}