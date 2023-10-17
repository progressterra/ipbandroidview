package com.progressterra.ipbandroidview.processes.occupacion

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.IncomeDataIDRFInterest
import com.progressterra.ipbandroidapi.api.iamhere.models.StatusResult
import com.progressterra.ipbandroidview.entities.Interest
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface SaveOccupationUseCase {

    suspend operator fun invoke(new: Interest, prev: Interest?): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken, private val service: ImhService
    ) : SaveOccupationUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(new: Interest, prev: Interest?): Result<Unit> =
            withToken { token ->
                val results = mutableListOf<StatusResult?>()
                prev?.let {
                    results.add(
                        service.clientInterestDelete(
                            token = token, body = IncomeDataIDRFInterest(prev.id)
                        ).result?.status
                    )
                }
                results.add(
                    service.clientInterest(
                        token = token, body = IncomeDataIDRFInterest(new.id)
                    ).result?.status
                )
                results.forEach {
                    if (it != StatusResult.SUCCESS) throw Exception("Occupation cannot be saved!")
                }
            }
    }
}