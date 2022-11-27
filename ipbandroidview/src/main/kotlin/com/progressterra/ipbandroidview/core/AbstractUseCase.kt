package com.progressterra.ipbandroidview.core

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.IncomeDataCreateAccessToken
import com.progressterra.ipbandroidview.data.UserData

abstract class AbstractUseCase(
    private val sCRMRepository: SCRMRepository, private val provideLocation: ProvideLocation
) {

    protected suspend fun <T> withToken(
        block: suspend (accessToken: String) -> T
    ): Result<T> = runCatching {
        val locationResult = provideLocation.location()
        val result = sCRMRepository.accessToken(
            IncomeDataCreateAccessToken(
                idDevice = UserData.deviceId,
                latitude = locationResult.getOrNull()?.latitude?.toFloat() ?: 0f,
                longitude = locationResult.getOrNull()?.longitude?.toFloat() ?: 0f
            )
        ).getOrThrow()
        block(result ?: "")
    }
}