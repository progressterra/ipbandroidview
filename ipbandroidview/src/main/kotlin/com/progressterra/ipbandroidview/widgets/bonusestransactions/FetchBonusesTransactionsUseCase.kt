package com.progressterra.ipbandroidview.widgets.bonusestransactions

import com.progressterra.ipbandroidapi.api.ibonus.IBonusRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidapi.ext.parseToDate
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.bonustransaction.BonusTransactionState
import com.progressterra.ipbandroidview.features.bonustransaction.BonusTransactionType
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface FetchBonusesTransactionsUseCase {

    suspend operator fun invoke(): Result<BonusesTransactionsState>

    class Base(
        sCRMRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        manageResources: ManageResources,
        private val bonusesRepository: IBonusRepository
    ) : FetchBonusesTransactionsUseCase, AbstractUseCase(sCRMRepository, provideLocation) {

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

    class Test : FetchBonusesTransactionsUseCase {

        override suspend fun invoke(): Result<BonusesTransactionsState> = runCatching {
            BonusesTransactionsState(
                transactions = listOf(
                    BonusTransactionState(
                        date = "2021-01-01",
                        amount = "100",
                        type = BonusTransactionType.BURNING
                    ),
                    BonusTransactionState(
                        date = "2021-01-02",
                        amount = "200",
                        type = BonusTransactionType.BUYING
                    ),
                    BonusTransactionState(
                        date = "2021-01-03",
                        amount = "300",
                        type = BonusTransactionType.BURNING
                    ),
                    BonusTransactionState(
                        date = "2021-01-04",
                        amount = "400",
                        type = BonusTransactionType.RECEIVING
                    )
                )
            )
        }
    }
}