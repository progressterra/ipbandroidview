package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.ibonus.IBonusRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidapi.ext.parseToDate
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.model.bonuses.Transaction

interface TransactionsUseCase {

    suspend operator fun invoke(): Result<List<Transaction>>

    class Base(
        sCRMRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        manageResources: ManageResources,
        private val bonusesRepository: IBonusRepository
    ) : TransactionsUseCase, AbstractUseCase(sCRMRepository, provideLocation) {

        private val noData = manageResources.string(R.string.no_data)

        //TODO transaction type when + when -
        override suspend fun invoke(): Result<List<Transaction>> = withToken { token ->
            bonusesRepository.getTransactionsList(token).getOrThrow()?.map {
                Transaction(
                    date = it.dateEvent?.parseToDate()?.format("dd.MM.yyyy") ?: noData,
                    name = it.typeBonusName ?: noData,
                    delta = it.quantity?.toInt() ?: 0
                )
            } ?: emptyList()
        }
    }
}