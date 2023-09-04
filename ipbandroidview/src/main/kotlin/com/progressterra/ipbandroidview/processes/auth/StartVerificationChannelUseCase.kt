package com.progressterra.ipbandroidview.processes.auth

import com.progressterra.ipbandroidapi.IpbAndroidApiSettings
import com.progressterra.ipbandroidapi.api.auth.AuthService
import com.progressterra.ipbandroidapi.api.auth.models.IncomeDataStartChannelVerification
import com.progressterra.ipbandroidview.entities.SignInData

interface StartVerificationChannelUseCase {

    suspend operator fun invoke(phoneNumber: String): Result<SignInData>

    class Base(
        private val authService: AuthService
    ) : StartVerificationChannelUseCase {

        override suspend fun invoke(phoneNumber: String): Result<SignInData> = runCatching {
            val formattedPhoneNumber = phoneNumber.trim()
            val result = authService.loginStart(
                IncomeDataStartChannelVerification(
                    phone = formattedPhoneNumber,
                    accessKeyEnterprise = IpbAndroidApiSettings.ACCESS_KEY
                )
            )
            SignInData(
                phone = formattedPhoneNumber,
                allowedAttempts = result.data?.numberAttemptsLeft ?: 0,
                secondsToResend = result.data?.secondForResendSMS ?: 0
            )
        }
    }
}