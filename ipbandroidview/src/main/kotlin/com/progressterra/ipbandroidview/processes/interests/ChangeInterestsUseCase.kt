package com.progressterra.ipbandroidview.processes.interests

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.IncomeDataIDRFInterest
import com.progressterra.ipbandroidapi.api.iamhere.models.StatusResult
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Interest
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface ChangeInterestsUseCase {

    suspend operator fun invoke(user: List<Interest>, changed: List<Interest>): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val service: ImhService, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : ChangeInterestsUseCase, AbstractTokenUseCase(
        obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(user: List<Interest>, changed: List<Interest>): Result<Unit> = withToken { token ->
            changed.forEach {
                if (user.contains(it)) {
                    if (service.clientInterestDelete(
                            token = token,
                            body = IncomeDataIDRFInterest(it.id)
                        ).result?.status != StatusResult.SUCCESS
                    ) throw ToastedException(R.string.failure)
                } else {
                    if (service.clientInterest(
                            token = token,
                            body = IncomeDataIDRFInterest(it.id)
                        ).result?.status != StatusResult.SUCCESS
                    ) throw ToastedException(R.string.failure)
                }
            }
        }
    }
}