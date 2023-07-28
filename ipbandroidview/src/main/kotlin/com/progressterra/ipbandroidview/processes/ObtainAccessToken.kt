package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.IncomeDataCreateAccessToken
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.UserData

interface ObtainAccessToken {

    suspend operator fun invoke(): Result<String>

    class Base(
        private val scrmRepository: SCRMRepository,
        private val provideLocation: ProvideLocation
    ) : ObtainAccessToken {

        override suspend fun invoke(): Result<String> = runCatching {
            val locationResult = provideLocation.location()
            scrmRepository.accessToken(
                IncomeDataCreateAccessToken(
                    idDevice = UserData.deviceId,
                    latitude = locationResult.getOrNull()?.latitude?.toFloat() ?: 0f,
                    longitude = locationResult.getOrNull()?.longitude?.toFloat() ?: 0f
                )
            ).getOrThrow()!!
        }
    }
}