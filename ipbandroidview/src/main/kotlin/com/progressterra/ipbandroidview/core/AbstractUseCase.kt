package com.progressterra.ipbandroidview.core

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.IncomeDataCreateAccessToken
import com.progressterra.ipbandroidapi.user.UserData

abstract class AbstractUseCase(
    private val sCRMRepository: SCRMRepository, private val provideLocation: ProvideLocation
) {

    protected suspend fun <T> withToken(block: suspend (accessToken: String) -> Result<T>): Result<T> {
        val locationResult = provideLocation.location()
        val result = sCRMRepository.accessToken(
            IncomeDataCreateAccessToken(
                idDevice = UserData.deviceId,
                latitude = locationResult.getOrNull()?.latitude?.toFloat() ?: 0f,
                longitude = locationResult.getOrNull()?.longitude?.toFloat() ?: 0f
            )
        ).onFailure { return Result.failure(it) }
        return block.invoke(result.getOrNull()!!)
    }
}