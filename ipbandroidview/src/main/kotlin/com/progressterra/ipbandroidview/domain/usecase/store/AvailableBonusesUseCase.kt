package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.ibonus.IBonusRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.model.BonusesInfo

interface AvailableBonusesUseCase {

    suspend fun availableBonuses(): Result<BonusesInfo>

    class Base(
        sCRMRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        manageResources: ManageResources,
        private val bonusesRepository: IBonusRepository
    ) : AvailableBonusesUseCase, AbstractUseCase(sCRMRepository, provideLocation) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun availableBonuses(): Result<BonusesInfo> = runCatching {
            val response = withToken { bonusesRepository.getGeneralInfo(it) }.getOrThrow()
            BonusesInfo(
                quantity = response?.currentQuantity?.toInt() ?: 0,
                forBurningQuantity = response?.forBurningQuantity?.toInt() ?: 0,
                burningDate = response?.dateBurning?.format("dd.MM") ?: noData
            )
        }
    }
}