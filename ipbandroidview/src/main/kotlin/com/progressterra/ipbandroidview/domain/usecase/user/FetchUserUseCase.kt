package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.parseToDate
import com.progressterra.ipbandroidview.data.UserData
import com.progressterra.ipbandroidview.data.UserName
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation

interface FetchUserUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        private val scrmRepository: SCRMRepository
    ) : AbstractUseCase(scrmRepository, provideLocation), FetchUserUseCase {

        override suspend fun invoke(): Result<Unit> = withToken { token ->
            val info = scrmRepository.clientInfoByToken(token).getOrThrow()
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
        }
    }
}