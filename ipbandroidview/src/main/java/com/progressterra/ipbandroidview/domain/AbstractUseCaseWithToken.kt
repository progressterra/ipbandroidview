package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidview.base.ProvideLocation

abstract class AbstractUseCaseWithToken(
    private val sCRMRepository: SCRMRepository,
    private val provideLocation: ProvideLocation
) : AbstractUseCase() {

    protected suspend fun <T> withToken(block: suspend (accessToken: String) -> T): Result<T> {
        val location = provideLocation.location()
        val result = sCRMRepository.getAccessToken(
            UserData.deviceId,
            location.latitude.toFloat(),
            location.longitude.toFloat()
        )
        if (result.isFailure)
            return Result.failure(result.exceptionOrNull()!!)
        return handle { block.invoke(result.getOrNull()!!) }
    }
}