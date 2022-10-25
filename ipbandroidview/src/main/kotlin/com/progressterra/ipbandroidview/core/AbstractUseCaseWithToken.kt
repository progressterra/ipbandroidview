package com.progressterra.ipbandroidview.core

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidview.data.ProvideLocation

abstract class AbstractUseCaseWithToken(
    private val sCRMRepository: SCRMRepository,
    private val provideLocation: ProvideLocation
) {

    protected suspend fun <T> withToken(block: suspend (accessToken: String) -> Result<T>): Result<T> {
        val locationResult = provideLocation.location()
        val result = sCRMRepository.getAccessToken(
            UserData.deviceId,
            locationResult.getOrNull()?.latitude?.toFloat() ?: 0f,
            locationResult.getOrNull()?.longitude?.toFloat() ?: 0f
        ).onFailure { return Result.failure(it) }
        return block.invoke(result.getOrNull()!!)
    }
}