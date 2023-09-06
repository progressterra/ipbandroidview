package com.progressterra.ipbandroidview.widgets.bonusestransactions

import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface FetchBonusesTransactionsUseCase {

    suspend operator fun invoke(): Result<BonusesTransactionsState>

    class Base(
        obtainAccessToken: ObtainAccessToken,
    ) : FetchBonusesTransactionsUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<BonusesTransactionsState> = withToken { token ->
            BonusesTransactionsState(emptyList())
        }
    }
}