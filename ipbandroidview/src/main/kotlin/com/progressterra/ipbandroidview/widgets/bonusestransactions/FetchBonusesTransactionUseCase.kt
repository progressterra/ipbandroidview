package com.progressterra.ipbandroidview.widgets.bonusestransactions

import com.progressterra.ipbandroidapi.api.ibonus.IBonusRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidapi.ext.parseToDate
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.features.bonustransaction.BonusTransactionState
import com.progressterra.ipbandroidview.features.bonustransaction.BonusTransactionType

interface FetchBonusesTransactionUseCase {

    suspend operator fun invoke(): Result<BonusesTransactionsState>

    class Base(
        sCRMRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        manageResources: ManageResources,
        private val bonusesRepository: IBonusRepository
    ) : FetchBonusesTransactionUseCase, AbstractUseCase(sCRMRepository, provideLocation) {

        private val noData = manageResources.string(R.string.no_data)

        //TODO transaction type when + when -
        override suspend fun invoke(): Result<BonusesTransactionsState> = withToken { token ->
            val transactions = buildList {
                bonusesRepository.getTransactionsList(token).getOrThrow()?.map {
                    add(
                        BonusTransactionState(
                            date = it.dateEvent?.parseToDate()?.format("dd.MM.yyyy") ?: noData,
                            amount = it.quantity?.toString() ?: noData,
                            type = BonusTransactionType.BURNING
                        )
                    )
                } ?: emptyList()
            }
            BonusesTransactionsState(transactions)
        }
    }
}