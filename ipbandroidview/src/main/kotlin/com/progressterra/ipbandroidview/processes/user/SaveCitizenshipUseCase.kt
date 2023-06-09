package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidview.entities.Citizenship
import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.shared.UserData

interface SaveCitizenshipUseCase {

    suspend operator fun invoke(citizenship: Citizenship): Result<Unit>

    class Base : SaveCitizenshipUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(citizenship: Citizenship): Result<Unit> = handle {
            UserData.citizenship = citizenship
        }
    }
}