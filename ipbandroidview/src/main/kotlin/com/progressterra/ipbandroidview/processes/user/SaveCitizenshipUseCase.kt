package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidview.processes.data.CitizenshipRepository
import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.shared.UserData

interface SaveCitizenshipUseCase {

    suspend operator fun invoke(name: String): Result<Unit>

    class Base(
        private val citizenshipRepository: CitizenshipRepository
    ) : SaveCitizenshipUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(name: String): Result<Unit> = handle {
            UserData.citizenship = name
            UserData.docSpecId = citizenshipRepository.idByName(name)
        }
    }
}