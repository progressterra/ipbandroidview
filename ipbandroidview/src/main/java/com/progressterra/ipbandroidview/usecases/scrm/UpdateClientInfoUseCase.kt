package com.progressterra.ipbandroidview.usecases.scrm

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.ui.personal_edit.models.ClientInfoUI
import com.progressterra.ipbandroidview.ui.personal_edit.models.ClientUpdateInfo

interface UpdateClientInfoUseCase {

    suspend fun updateInfo(info: ClientUpdateInfo): Result<ClientInfoUI>

    class Base(
        private val sCRMRepository: SCRMRepository
    ) : UpdateClientInfoUseCase {

        override suspend fun updateInfo(info: ClientUpdateInfo): Result<ClientInfoUI> {
            val tokenResult = sCRMRepository.getAccessToken()
            if (tokenResult.isFailure)
                return Result.failure(tokenResult.exceptionOrNull()!!)
            val clientInfoResult = sCRMRepository.setPersonalInfo(
                accessToken = tokenResult.getOrNull()!!,
                soname = info.soname,
                name = info.name
            )
            if (clientInfoResult.isFailure)
                return Result.failure(clientInfoResult.exceptionOrNull()!!)
            val setEmailResult = sCRMRepository.setEmail(tokenResult.getOrNull()!!, info.email)
            if (setEmailResult.isFailure && setEmailResult.getOrNull() != false)
                return Result.failure(setEmailResult.exceptionOrNull()!!)
            return Result.success(
                ClientInfoUI(
                    clientInfoResult.getOrNull()!!.client.name +
                            " ${clientInfoResult.getOrNull()!!.client.soname}" +
                            " ${clientInfoResult.getOrNull()!!.client.patronymic}",
                    info.email,
                    clientInfoResult.getOrNull()!!.client.name,
                    clientInfoResult.getOrNull()!!.client.soname
                )
            )
        }
    }
}