package com.progressterra.ipbandroidview.processes.utils

import com.progressterra.ipbandroidapi.IpbAndroidApiSettings
import com.progressterra.ipbandroidapi.api.auth.AuthService
import com.progressterra.ipbandroidapi.api.auth.models.IncomeDataCreateAccessToken
import com.progressterra.ipbandroidview.processes.location.ProvideLocationUseCase
import com.progressterra.ipbandroidview.shared.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.shared.UserData

interface ObtainAccessToken {

    suspend operator fun invoke(): Result<String>

    class Base(
        private val authService: AuthService,
        private val provideLocationUseCase: ProvideLocationUseCase
    ) : ObtainAccessToken {

        override suspend fun invoke(): Result<String> = runCatching {
            if (!UserData.clientExist) {
                IpbAndroidViewSettings.ACCESS_TOKEN_FOR_UNAUTHORIZED_USER
            }
            val locationResult = provideLocationUseCase()
            authService.accessToken(
                IncomeDataCreateAccessToken(
                    idDevice = UserData.deviceId,
                    latitude = locationResult.getOrNull()?.latitude ?: 0.0,
                    longitude = locationResult.getOrNull()?.longitude ?: 0.0,
                    accessKey = IpbAndroidApiSettings.ACCESS_KEY
                )
            ).data!!
        }
    }
}