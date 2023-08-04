package com.progressterra.ipbandroidview.widgets.bonusestransactions

import com.progressterra.ipbandroidapi.api.ibonus.IBonusRepository
import com.progressterra.ipbandroidview.entities.format
import com.progressterra.ipbandroidview.entities.parseToDate
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.bonustransaction.BonusTransactionState
import com.progressterra.ipbandroidview.features.bonustransaction.BonusTransactionType
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface FetchBonusesTransactionsUseCase {

    suspend operator fun invoke(): Result<BonusesTransactionsState>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        manageResources: ManageResources,
        private val bonusesRepository: IBonusRepository
    ) : FetchBonusesTransactionsUseCase, AbstractTokenUseCase(obtainAccessToken) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun invoke(): Result<BonusesTransactionsState> = withToken { token ->
            val transactions = buildList {
                bonusesRepository.getTransactionsList(token).getOrThrow()?.map {
                    add(
                        BonusTransactionState(
                            date = it.dateEvent?.parseToDate()?.format("dd.MM.yyyy") ?: noData,
                            amount = it.quantity?.toInt()?.toString() ?: noData,
                            type = BonusTransactionType.BURNING
                        )
                    )
                } ?: emptyList()
            }
            BonusesTransactionsState(transactions)
        }
    }
}