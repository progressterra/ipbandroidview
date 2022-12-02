package com.progressterra.ipbandroidview.domain.usecase.bonus

import com.progressterra.ipbandroidapi.api.ibonus.IBonusRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidapi.ext.parseToDate
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.model.BonusesInfo

interface AvailableBonusesUseCase {

    suspend operator fun invoke(): Result<BonusesInfo>

    class Base(
        sCRMRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        manageResources: ManageResources,
        private val bonusesRepository: IBonusRepository
    ) : AvailableBonusesUseCase, AbstractUseCase(sCRMRepository, provideLocation) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun invoke(): Result<BonusesInfo> = withToken { token ->
            val response = bonusesRepository.getGeneralInfo(token).getOrThrow()
            BonusesInfo(
                quantity = response?.currentQuantity?.toInt() ?: 0,
                forBurningQuantity = response?.forBurningQuantity?.toInt() ?: 0,
                burningDate = response?.dateBurning?.parseToDate()?.format("dd.MM") ?: noData
            )
        }
    }
}