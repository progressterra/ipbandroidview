package com.progressterra.ipbandroidview.features.proshkabonuses

import com.progressterra.ipbandroidapi.api.ibonus.IBonusRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidapi.ext.parseToDate
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.shared.ProvideLocation

interface ProshkaBonusesUseCase {

    suspend operator fun invoke(): Result<ProshkaBonusesState>

    class Base(
        sCRMRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        manageResources: ManageResources,
        private val bonusesRepository: IBonusRepository
    ) : ProshkaBonusesUseCase, AbstractUseCase(sCRMRepository, provideLocation) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun invoke(): Result<ProshkaBonusesState> = withToken { token ->
            val response = bonusesRepository.getGeneralInfo(token).getOrThrow()
            ProshkaBonusesState(
                bonuses = response?.currentQuantity?.toString() ?: noData,
                burningQuantity = response?.forBurningQuantity?.toString() ?: noData,
                burningDate = response?.dateBurning?.parseToDate()?.format("dd.MM") ?: noData
            )
        }
    }
}