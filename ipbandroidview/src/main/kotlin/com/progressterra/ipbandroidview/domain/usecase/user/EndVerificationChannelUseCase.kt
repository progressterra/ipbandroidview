package com.progressterra.ipbandroidview.domain.usecase.user

import android.os.Build
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.IncomeDataForEndLogin
import com.progressterra.ipbandroidapi.ext.parseToDate
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.data.UserData
import com.progressterra.ipbandroidview.data.UserName
import com.progressterra.ipbandroidview.ext.throwOnFailure

interface EndVerificationChannelUseCase {

    suspend operator fun invoke(phoneNumber: String, code: String): Result<Unit>

    class Base(
        private val repo: SCRMRepository, provideLocation: ProvideLocation
    ) : EndVerificationChannelUseCase, AbstractUseCase(repo, provideLocation) {

        override suspend fun invoke(phoneNumber: String, code: String): Result<Unit> = runCatching {
            var formattedPhoneNumber = phoneNumber.trim()
            if (formattedPhoneNumber.startsWith('8')) formattedPhoneNumber =
                formattedPhoneNumber.replaceFirst('8', '7')
            val result = repo.verificationChannelEnd(
                IncomeDataForEndLogin(
                    channelType = 0,
                    channelData = formattedPhoneNumber,
                    codeConfirm = code,
                    infoDevice = "SDK: ${Build.VERSION.SDK_INT} Model: ${Build.MODEL}"
                )
            ).getOrThrow()
            UserData.deviceId = result?.idDevice!!
            UserData.phone = formattedPhoneNumber
            UserData.clientExist = true
            withToken { token ->
                val info = repo.clientInfoByToken(token).getOrThrow()
                info?.client?.let {
                    UserData.idUnique = it.idUnique!!
                    UserData.userName = UserName(
                        name = it.name ?: "",
                        surname = it.soname ?: "",
                        patronymic = it.patronymic ?: ""
                    )
                    UserData.dateOfBirthday = it.dateOfBirth?.parseToDate()?.time ?: 0L
                }
                info?.clientAdditionalInfo?.let {
                    UserData.email = it.eMailGeneral ?: ""
                }
            }.throwOnFailure()
        }
    }
}