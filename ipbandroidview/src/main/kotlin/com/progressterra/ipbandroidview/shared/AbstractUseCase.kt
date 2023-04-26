package com.progressterra.ipbandroidview.shared

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.IncomeDataCreateAccessToken
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.Constants.DEFAULT_ID

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
        block(result ?: DEFAULT_ID)
    }
}