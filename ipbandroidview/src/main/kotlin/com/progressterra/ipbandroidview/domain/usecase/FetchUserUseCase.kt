package com.progressterra.ipbandroidview.domain.usecase

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.parseToDate
import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidapi.user.UserName
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation

interface FetchUserUseCase {

    suspend fun fetch(): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        private val scrmRepository: SCRMRepository
    ) : AbstractUseCase(scrmRepository, provideLocation), FetchUserUseCase {

        override suspend fun fetch(): Result<Unit> = withToken { token ->
            val info = scrmRepository.clientInfoByToken(token).getOrThrow()
            info?.client?.let {
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