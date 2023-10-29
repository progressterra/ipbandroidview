package com.progressterra.ipbandroidview.processes.interests

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.IncomeDataIDRFInterest
import com.progressterra.ipbandroidapi.api.iamhere.models.StatusResult
import com.progressterra.ipbandroidview.entities.Interest
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface ChangeInterestsUseCase {

    suspend operator fun invoke(data: List<Interest>): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val service: ImhService, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : ChangeInterestsUseCase, AbstractTokenUseCase(obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(data: List<Interest>): Result<Unit> = withToken { token ->
            data.forEach {
                if (if (it.picked) {
                        service.clientInterest(
                            token = token,
                            body = IncomeDataIDRFInterest(it.id)
                        )
                    } else {
                        service.clientInterestDelete(
                            token = token,
                            body = IncomeDataIDRFInterest(it.id)
                        )
                    }.result?.status != StatusResult.SUCCESS
                ) {
                    throw Exception("Bad request!")
                }
            }
        }
    }
}