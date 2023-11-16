package com.progressterra.ipbandroidview.processes.auth

import com.progressterra.ipbandroidapi.IpbAndroidApiSettings
import com.progressterra.ipbandroidapi.api.auth.AuthService
import com.progressterra.ipbandroidapi.api.auth.models.IncomeDataStartChannelVerification
import com.progressterra.ipbandroidapi.api.auth.models.StatusResult
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.SignInData
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources

interface StartVerificationChannelUseCase {

    suspend operator fun invoke(phoneNumber: String): Result<SignInData>

    class Base(
        private val authService: AuthService, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : StartVerificationChannelUseCase, AbstractLoggingUseCase(makeToastUseCase, manageResources) {

        override suspend fun invoke(phoneNumber: String): Result<SignInData> = handle {
            val formattedPhoneNumber = phoneNumber.trim()
            val result = authService.loginStart(
                IncomeDataStartChannelVerification(
                    phone = formattedPhoneNumber,
                    accessKeyEnterprise = IpbAndroidApiSettings.ACCESS_KEY
                )
            )
            if (result.result?.status != StatusResult.SUCCESS) throw ToastedException(R.string.wrong_phone)
            SignInData(
                token = result.data?.tempToken ?: "",
                phone = formattedPhoneNumber,
                allowedAttempts = result.data?.numberAttemptsLeft ?: 0,
                secondsToResend = result.data?.secondForResendSMS ?: 0
            )
        }
    }
}