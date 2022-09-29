package com.progressterra.ipbandroidview.base

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidview.data.ProvideLocation

abstract class AbstractUseCaseWithToken(
    private val sCRMRepository: SCRMRepository,
    private val provideLocation: ProvideLocation
) : AbstractUseCase() {

    protected suspend fun <T> withToken(block: suspend (accessToken: String) -> Result<T>): Result<T> {
        val location = provideLocation.location()
        val result = sCRMRepository.getAccessToken(
            UserData.deviceId,
            location.latitude.toFloat(),
            location.longitude.toFloat()
        )
        if (result.isFailure)
            return Result.failure(result.exceptionOrNull()!!)
        return block.invoke(result.getOrNull()!!)
    }
}