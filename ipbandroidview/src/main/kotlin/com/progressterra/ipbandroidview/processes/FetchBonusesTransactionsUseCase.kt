package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.widgets.bonusestransactions.BonusesTransactionsState

interface FetchBonusesTransactionsUseCase {

    suspend operator fun invoke(): Result<BonusesTransactionsState>

    class Base(
        obtainAccessToken: ObtainAccessToken, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources,
    ) : FetchBonusesTransactionsUseCase, AbstractTokenUseCase(obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(): Result<BonusesTransactionsState> = withToken { _ ->
            BonusesTransactionsState(emptyList())
        }
    }
}