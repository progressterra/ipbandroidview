package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidview.entities.Citizenship
import com.progressterra.ipbandroidview.shared.UserData

interface SaveCitizenshipUseCase {

    suspend operator fun invoke(citizenship: Citizenship)

    class Base : SaveCitizenshipUseCase {

        override suspend fun invoke(citizenship: Citizenship) {
            UserData.citizenship = citizenship
        }
    }
}