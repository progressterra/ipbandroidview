package com.progressterra.ipbandroidview.processes.occupacion

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.IncomeDataIDRFInterest
import com.progressterra.ipbandroidapi.api.iamhere.models.ResultOperation
import com.progressterra.ipbandroidapi.api.iamhere.models.StatusResult
import com.progressterra.ipbandroidview.entities.Interest
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface SaveOccupationUseCase {

    suspend operator fun invoke(new: Interest?, prev: Interest?): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken, private val service: ImhService,
        makeToastUseCase: MakeToastUseCase, manageResources: ManageResources
    ) : SaveOccupationUseCase, AbstractTokenUseCase(
        obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(new: Interest?, prev: Interest?): Result<Unit> =
            withToken { token ->
                val results = mutableListOf<ResultOperation?>()
                if (prev != null) {
                    results.add(
                        service.clientInterestDelete(
                            token = token, body = IncomeDataIDRFInterest(prev.id)
                        ).result
                    )
                }
                if (new != null) {
                    results.add(
                        service.clientInterest(
                            token = token, body = IncomeDataIDRFInterest(new.id)
                        ).result
                    )
                }
                results.forEach {
                    if (it?.status != StatusResult.SUCCESS) throw ToastedException(
                        it?.message ?: ""
                    )
                }
            }
    }
}